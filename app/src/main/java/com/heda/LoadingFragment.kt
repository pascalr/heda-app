package com.heda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.heda.adapters.RecipeAdapter
import com.heda.helpers.G
import com.heda.helpers.fetch
import com.heda.models.AppStatus
import com.heda.models.Recipe
import com.heda.view_models.Data
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.recipes_fragment.*

class LoadingFragment : Fragment(R.layout.fragment_loading) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetch("${G.host}/app_status", "GET") { response ->

            val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            val jsonAdapter: JsonAdapter<AppStatus> = moshi.adapter(AppStatus::class.java)
            val status: AppStatus? = jsonAdapter.fromJson(response);

            // TODO: Store csrf token

            val action = when (status?.logged_in) {
                "1" -> R.id.action_loadingFragment_to_appFragment3
                else -> R.id.action_loadingFragment_to_loginFragment
            }
            requireActivity().runOnUiThread(Runnable {
                findNavController().navigate(action)
            })
        }

//        fetch("${G.host}/am_i_logged_in", "GET") { response ->
//
//            val action = when (response) {
//                "1" -> R.id.action_loadingFragment_to_appFragment3
//                else -> R.id.action_loadingFragment_to_loginFragment
//            }
//            requireActivity().runOnUiThread(Runnable {
//                findNavController().navigate(action)
//            })
//        }
    }
}