package com.heda.view_models

import androidx.lifecycle.ViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

data class DataUser(
    val name: String
)

data class DataRecipe(
    val name: String?,
    val id: Int,
    val user_id: Int?,
    val recipe_kind_id: Int?,
    val preparation_time: Int?,
    val cooking_time: Int?,
    val total_time: Int?,
    val json: String?,
    val ingredients: String?,
    val image_slug: String?,
    val original_id: Int?,
    val is_public: Int?,
    val raw_servings: String?,
    val heda_instructions: String?
)

data class Data(
    val user: DataUser,
    val userRecipes: List<DataRecipe>,
    val favRecipes: List<DataRecipe>
)

fun getData(callback: (String) -> Unit) {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://www.hedacuisine.com/fetch_user/5")
        .build()

    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) throw IOException("Unexpected code $response")
        //for ((name, value) in response.headers) {}
        callback(response.body!!.string())
    }
}

/**
 * A view model persists based on it's scope.
 */
class DataViewModel: ViewModel() {

    var data: Data? = null;

    init {
        // Inside a thread, otherwise I get a NetworkOnMainThreadException
        val thread = Thread {
            try {
                getData { json ->
                    println("!!!!!!!!!!!!!!!!!!!!!!")
                    println("!!!!!!!!!!!!!!!!!!!!!!")
                    println("!!!!!!!!!!!!!!!!!!!!!!")
                    println("!!!!!!!!!!!!!!!!!!!!!!")
                    println("!!!!!!!!!!!!!!!!!!!!!!")
                    println("!!!!!!!!!!!!!!!!!!!!!!")
                    println("!!!!!!!!!!!!!!!!!!!!!!")
                    println(json)

                    val moshi = Moshi.Builder()
                        .addLast(KotlinJsonAdapterFactory())
                        .build()
                    //val jsonAdapter: JsonAdapter<Data> = moshi.adapter<Data>()
                    val jsonAdapter: JsonAdapter<Data> = moshi.adapter(Data::class.java)

                    data = jsonAdapter.fromJson(json);
                    // TODO: Bind data to recycler view...
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
        // FIXME: I don't understand why this does not work...
        //viewModelScope.launch {
        //    getData { data ->
        //        println(data)
        //    }
        //}
    }
}