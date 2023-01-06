package com.heda.helpers

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.heda.R
import com.heda.models.Ingredient

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