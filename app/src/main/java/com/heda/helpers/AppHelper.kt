package com.heda.helpers

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.heda.R

fun toastShort(context: Context?, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun changePage(fragmentManager: FragmentManager, id: Int) {
    changePage(fragmentManager, Fragment(id))
}

fun changePage(fragmentManager: FragmentManager, frag: Fragment) {
    fragmentManager.beginTransaction().apply {
        replace(R.id.flMain, frag)
        addToBackStack(null)
        commit()
    }
}