package com.heda

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.heda.helpers.changeTab
import kotlinx.android.synthetic.main.app.*

class AppFragment : Fragment(R.layout.app) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.beginTransaction().apply {
            replace(R.id.flApp, HomeFragment())
            commit()
        }

        tlApp.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null && tab.position == 0) {
                    changeTab(parentFragmentManager, HomeFragment())
                }
                if (tab != null && tab.position == 1) {
                    changeTab(parentFragmentManager, RecipesFragment())
                    //navController.navigate(R.id.action_HomeFragment_to_RecipesFragment)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

}