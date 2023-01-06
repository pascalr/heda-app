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
    changeFragment(fragmentManager, R.id.flMain, Fragment(id))
}

fun changePage(fragmentManager: FragmentManager, frag: Fragment) {
    changeFragment(fragmentManager, R.id.flMain, frag)
}

fun changeTab(fragmentManager: FragmentManager, id: Int) {
    changeFragment(fragmentManager, R.id.flApp, Fragment(id))
}

fun changeTab(fragmentManager: FragmentManager, frag: Fragment) {
    changeFragment(fragmentManager, R.id.flApp, frag)
}

fun clearBackStack(manager: FragmentManager) {
    TODO("FIXME: This does not work... manager.backStackEntryCount is somehow at 0...")
    //while (manager.backStackEntryCount > 0) {
    //    println(manager.backStackEntryCount)
    //    manager.popBackStackImmediate();
    //}
}

private fun changeFragment(fragmentManager: FragmentManager, id: Int, frag: Fragment) {
    fragmentManager.beginTransaction().apply {
        replace(id, frag)
        addToBackStack(null)
        commit()
    }
}