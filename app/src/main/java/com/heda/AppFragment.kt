package com.heda

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.heda.helpers.changeTab
import kotlinx.android.synthetic.main.app.*
import kotlinx.android.synthetic.main.toolbar.*

class AppFragment : Fragment(R.layout.app) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.beginTransaction().apply {
            replace(R.id.flApp, HomeFragment())
            commit()
        }

        imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        tlApp.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when (tab.position) {
                        0 -> changeTab(parentFragmentManager, HomeFragment())
                        1 -> changeTab(parentFragmentManager, RecipesFragment())
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

}