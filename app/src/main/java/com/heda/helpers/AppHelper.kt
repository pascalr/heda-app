package com.heda.helpers

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.heda.R

fun toastShort(context: Context?, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun setToolbarTitle(activity: FragmentActivity, title: String) {
    val tvToolbarName: TextView = activity.findViewById(R.id.tvToolbarTitle)
    tvToolbarName.text = title
}