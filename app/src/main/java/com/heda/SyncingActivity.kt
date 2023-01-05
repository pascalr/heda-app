package com.heda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

fun getData() {
    Thread.sleep(10000)

    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://www.hedacuisine.com/fetch_user/5")
        .build()

    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) throw IOException("Unexpected code $response")

        for ((name, value) in response.headers) {
            println("$name: $value")
        }

        println(response.body!!.string())
    }
}

class SyncingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.syncing_waiting_screen)

        //getData()
    }
}