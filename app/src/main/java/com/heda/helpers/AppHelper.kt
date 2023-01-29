package com.heda.helpers

import android.content.Context
import android.content.res.Resources
import android.net.Uri.Builder
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RawRes
import androidx.fragment.app.FragmentActivity
import com.heda.R
import com.heda.models.Ingredient
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException


fun toastShort(context: Context?, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun setToolbarTitle(activity: FragmentActivity, title: String) {
    val tvToolbarName: TextView = activity.findViewById(R.id.tvToolbarTitle)

    tvToolbarName.text = title
}

fun parseIngredients(raw: String?): List<Ingredient> {
    if (raw == null) {
        return listOf()
    }

    val ings = raw.split('\n')
    return ings.mapNotNull { str ->
        if (str.indexOf(';') != -1) {
            val s = str.split(';')
            Ingredient(s[0], s[1])
        } else {
            null
        }
    }
}

fun loadImage(imageSlug: String?, imageView: ImageView) {
    if (imageSlug != null) {
        val url = "${G.host}/imgs/original/"+imageSlug
        Picasso.get().load(url).into(imageView);
//        Picasso.get().load(url).into(object : com.squareup.picasso.Target {
//            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
//                if (bitmap != null) {
//                    imageView.setImageBitmap(bitmap)
//                }
//            }
//            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
//            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
//        })
    }
}

fun normalizeText(text: String): String {
    // TODO: Remove accents
    return text.lowercase()
}

fun searchScore(text: String, tokens: List<String>): Double {
    val t = normalizeText(text)
    var score = -1.0
    tokens.forEach { token ->
        if (t.contains(token)) {
            score += 10.0
        }
    }
    return score
}

// Source: https://stackoverflow.com/questions/4087674/android-read-text-raw-resource-file
fun Resources.getRawTextFile(@RawRes id: Int) =
    openRawResource(id).bufferedReader().use { it.readText() }

private fun query(url: String, method: String, data: RequestBody?, success: (String) -> Unit) {
    val thread = Thread {
        try {
            val client = OkHttpClient()
            val request = Request.Builder().url(url).method(method, data).build()
            println("***************** Query *****************")
            client.newCall(request).execute().use { response ->
                println("QUERY RESPONSE")
                println(response)
                if (!response.isSuccessful) throw IOException("Unexpected code $response")
                success(response.body!!.string())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    thread.start()
}

fun post(url: String, data: RequestBody, success: (String) -> Unit) {
    query(url, "POST", data, success)
}

fun fetch(url: String, method: String, success: (String) -> Unit) {
    query(url, method, null, success)
}