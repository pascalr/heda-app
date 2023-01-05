package com.heda

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

data class FetchDataUser(
    val name: String
)

data class FetchDataRecipe(
    val name: String,
    val id: Int,
    val user_id: Int,
    val recipe_kind_id: Int,
    val preparation_time: Int,
    val cooking_time: Int,
    val total_time: Int,
    val json: String,
    val ingredients: String,
    val image_slug: String,
    val original_id: Int,
    val is_public: Int,
    val raw_servings: String,
    val heda_instructions: String
)

data class FetchData(
    val user: FetchDataUser,
    val userRecipes: List<FetchDataRecipe>,
    val favRecipes: List<FetchDataRecipe>
)

fun getData(callback: (String) -> Any?) {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://www.hedacuisine.com/fetch_user/5")
        .build()

    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) throw IOException("Unexpected code $response")

        for ((name, value) in response.headers) {
            println("$name: $value")
        }

        val data = response.body!!.string()
        callback(data)
    }
}

/**
 * A view model persists based on it's scope.
 */
class FetchDataViewModel: ViewModel() {

    init {
        val thread = Thread {
            try {
                getData { data ->
                    println(data)
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

class StartActivity : AppCompatActivity() {

    private val pageHistory: MutableList<Int> = mutableListOf()

    private fun changePage(page: Int) {
        pageHistory.add(pageHistory.size, page)
        setContentView(page)
    }

    override fun onBackPressed() {
        if (pageHistory.size <= 1) {
            super.onBackPressed()
        } else {
            setContentView(pageHistory[pageHistory.size-2])
            pageHistory.removeLast()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(10000)

        //Toast.makeText(applicationContext, "StartActivity onCreate", Toast.LENGTH_SHORT).show()

        // How to share data between activities: Try:
        // https://medium.com/android-news/kotlin-series-share-data-between-activities-using-explicit-intent-5a963cceaceb

        // Maybe use: onSaveInstanceState and onRestoreInstanceState like here:
        // https://www.tutorialspoint.com/how-to-save-an-android-activity-state-using-save-instance-state

        val fetchDataViewModel = ViewModelProvider(this)[FetchDataViewModel::class.java]

        val loggedIn = true

        if (loggedIn) {
            changePage(R.layout.activity_main)
            //changePage(R.layout.home_fragment)
        //    val i = Intent(this@StartActivity, LoginActivity::class.java)
        //    startActivity(i)
        } else {
            changePage(R.layout.login_page)

            val button: Button = findViewById(R.id.btnRegister)
            button.setOnClickListener {
                changePage(R.layout.register_page)
            }
        //    val i = Intent(this@StartActivity, SyncingActivity::class.java)
        //    startActivity(i)
        }
    }
}