package com.heda


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.heda.helpers.setToolbarTitle
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(R.layout.home_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarTitle(requireActivity(), "HedaCuisine")

        //tlApp.getTabAt(router.tabPosition)?.select()

        ivSpeakButton.setOnClickListener {
            (requireActivity() as MainActivity).recognizeSpeech { spoken ->
                findNavController().navigate(R.id.action_global_searchFragment, searchBundle(spoken))
            }
        }

        tlApp.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when (tab.position) {
                        0 -> findNavController().navigate(R.id.action_global_homeFragment)
                        1 -> findNavController().navigate(R.id.action_global_recipesFragment)
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