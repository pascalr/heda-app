package com.heda.view_models

import androidx.lifecycle.ViewModel
import com.heda.models.Recipe
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.IOException

data class DataUser(
    val name: String
)

data class Data(
    val user: DataUser? = null,
    val userRecipes: List<Recipe>? = null,
    val favRecipes: List<Recipe>? = null
)

/**
 * A view model persists based on it's scope.
 */
class DataViewModel: ViewModel() {

    var fetching: Boolean = false;
    private var data: Data? = null;

    private fun jsonToData(json: String): Data? {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        //val jsonAdapter: JsonAdapter<Data> = moshi.adapter<Data>()
        val jsonAdapter: JsonAdapter<Data> = moshi.adapter(Data::class.java)

        return jsonAdapter.fromJson(json);
    }

    private fun fetchData(callback: (String) -> Unit) {
        if (!fetching) {
            fetching = true
            // Inside a thread, otherwise I get a NetworkOnMainThreadException
            val thread = Thread {
                try {
                    val client = OkHttpClient()
                    val request = Request.Builder()
                        .url("https://www.hedacuisine.com/fetch_user/5")
                        .build()

                    println("***************** Fetching data *****************")
                    client.newCall(request).execute().use { response ->
                        if (!response.isSuccessful) throw IOException("Unexpected code $response")
                        callback(response.body!!.string())
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            thread.start()
        }
    }

    private fun readDataFromFile(rootDir: File?, callback: (Data) -> Unit): Boolean {

        //val folder = File(rootDir, "data")
        //folder.mkdirs()
        //println(folder.exists()) // u'll get true

        val file = File(rootDir, "data.json")
        if (file.exists()) {
            println("***************** Reading data from file *****************")
            data = jsonToData(file.readText())
            callback(data ?: Data())
        } else {
            fetchData {json ->
                file.writeText(json)
                data = jsonToData(json)
                callback(data ?: Data())
            }
        }

        return false
    }

    fun getData(rootDir: File?, callback: (Data) -> Unit) {
        if (data != null) {
            callback(data ?: Data())
        } else {
            readDataFromFile(rootDir, callback)
        }
        // FIXME: I don't understand why this does not work...
        //viewModelScope.launch {
        //    getData { data ->
        //        println(data)
        //    }
        //}
    }
}