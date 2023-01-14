package com.heda.helpers

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RawRes
import androidx.fragment.app.FragmentActivity
import com.heda.R
import com.heda.models.Ingredient
import com.heda.models.Node
import com.heda.view_models.Data
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso

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

const val BASE_WEBSITE_URL = "https://www.hedacuisine.com/"

fun loadImage(imageSlug: String?, imageView: ImageView) {
    if (imageSlug != null) {
        val url = BASE_WEBSITE_URL+"imgs/original/"+imageSlug
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

fun parseInstructions(json: String): String {
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    val jsonAdapter: JsonAdapter<Node> = moshi.adapter(Node::class.java)
    val doc = jsonAdapter.fromJson(json);
    return "Todo print instructions"
}

// Source: https://stackoverflow.com/questions/4087674/android-read-text-raw-resource-file
fun Resources.getRawTextFile(@RawRes id: Int) =
    openRawResource(id).bufferedReader().use { it.readText() }
