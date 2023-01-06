package com.heda.view_models

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.heda.HomeFragment
import com.heda.LoginFragment
import com.heda.R

class RouterViewModel : ViewModel() {

    private var currentPageCreator: () -> Fragment = {-> LoginFragment()}
    private var currentTabCreator: () -> Fragment = {-> HomeFragment()}
    var tabPosition: Int = 0

    fun createCurrentPage(): Fragment {
        return currentPageCreator()
    }

    fun createCurrentTab(): Fragment {
        return currentTabCreator()
    }

    fun changePage(fragmentManager: FragmentManager, create: () -> Fragment) {
        changeFragment(fragmentManager, R.id.flMain, create)
    }

    fun changeTab(fragmentManager: FragmentManager, tabPos: Int, create: () -> Fragment) {
        changeFragment(fragmentManager, R.id.flApp, create)
        tabPosition = tabPos
    }

    fun clearBackStack(manager: FragmentManager) {
        // FIXME: This does not work... manager.backStackEntryCount is somehow at 0...
        //while (manager.backStackEntryCount > 0) {
        //    println(manager.backStackEntryCount)
        //    manager.popBackStackImmediate();
        //}
    }

    private fun changeFragment(fragmentManager: FragmentManager, id: Int, create: () -> Fragment) {
        fragmentManager.beginTransaction().apply {
            replace(id, create())
            addToBackStack(null)
            commit()
        }
        when (id) {
            R.id.flMain -> currentPageCreator = create
            R.id.flApp -> currentTabCreator = create
        }
    }

}

abstract class RoutingActivity : AppCompatActivity() {

    var currentPage: Fragment = LoginFragment()
    var currentTab: Fragment = HomeFragment()



}
