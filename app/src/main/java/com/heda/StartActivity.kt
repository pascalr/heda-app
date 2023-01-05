package com.heda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.heda.ui.login.LoginActivity
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

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Toast.makeText(applicationContext, "StartActivity onCreate", Toast.LENGTH_SHORT).show()

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://www.hedacuisine.com/fetch_user/5")
            .build()

        println("Hello world!!! Does this work?!?!")

        //client.newCall(request).execute().use { response ->
        //    if (!response.isSuccessful) throw IOException("Unexpected code $response")

            //for ((name, value) in response.headers) {
            //    println("$name: $value")
            //}

            //println(response.body!!.string())
        //}

        // How to share data between activities: Try:
        // https://medium.com/android-news/kotlin-series-share-data-between-activities-using-explicit-intent-5a963cceaceb

        // Maybe use: onSaveInstanceState and onRestoreInstanceState like here:
        // https://www.tutorialspoint.com/how-to-save-an-android-activity-state-using-save-instance-state

        val loggedIn = false
        if (loggedIn) {
            val i = Intent(this@StartActivity, LoginActivity::class.java)
            startActivity(i)
        } else {
            val i = Intent(this@StartActivity, MainActivity::class.java)
            startActivity(i)
        }
    }
}