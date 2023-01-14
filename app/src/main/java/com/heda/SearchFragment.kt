package com.heda

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.heda.adapters.SearchResultAdapter
import com.heda.helpers.normalizeText
import com.heda.helpers.searchScore
import com.heda.models.Recipe
import com.heda.view_models.DataViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.recipes_fragment.*

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var searchResultAdapter: SearchResultAdapter

    private var term = ""
    private var matches : List<Recipe>? = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        term = arguments?.getString("term") ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvSearchTerm.text = term

        val words = term.split(" ").map { s -> normalizeText(s) }

        val dataViewModel = ViewModelProvider(this)[DataViewModel::class.java]

        val rootDir = requireActivity().getExternalFilesDir(null)
        dataViewModel.getData(rootDir) { data ->

            matches = data.userRecipes?.filter {r -> r.name != null && searchScore(r.name, words) > 0 }

            requireActivity().runOnUiThread(Runnable {
                //val onClick = {recipe: Recipe -> router.changeTab(parentFragmentManager, 3) {-> ShowRecipeFragment.newInstance(recipe)}}
                //    fun newInstance(recipe: Recipe) = ShowRecipeFragment().apply {
                //        arguments = Bundle(1).apply {
                //            putSerializable("recipe", recipe)

                val onClick = {recipe: Recipe -> findNavController().navigate(R.id.action_searchFragment_to_showRecipeFragment, recipeBundle(recipe))}
                searchResultAdapter = SearchResultAdapter(matches?.toMutableList() ?: mutableListOf(), onClick)
                rvSearchItems.adapter = searchResultAdapter
                rvSearchItems.layoutManager = LinearLayoutManager(context)
            })
        }
    }
}

fun searchBundle(term: String) : Bundle {
    val bundle = Bundle()
    bundle.putSerializable("term", term)
    return bundle
}