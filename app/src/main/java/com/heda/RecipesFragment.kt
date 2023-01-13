package com.heda

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.heda.adapters.RecipeAdapter
import com.heda.helpers.setToolbarTitle
import com.heda.models.Recipe
import com.heda.view_models.DataViewModel
import com.heda.view_models.RouterViewModel
import kotlinx.android.synthetic.main.recipes_fragment.*

class RecipesFragment : Fragment(R.layout.recipes_fragment) {

    private lateinit var recipeAdapter: RecipeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val router = ViewModelProvider(requireActivity())[RouterViewModel::class.java]
        val dataViewModel = ViewModelProvider(this)[DataViewModel::class.java]

        setToolbarTitle(requireActivity(), requireContext().getString(R.string.recipes))

        val rootDir = requireActivity().getExternalFilesDir(null)
        dataViewModel.getData(rootDir) { data ->

            requireActivity().runOnUiThread(Runnable {
                //val onClick = {recipe: Recipe -> router.changeTab(parentFragmentManager, 3) {-> ShowRecipeFragment.newInstance(recipe)}}
                //    fun newInstance(recipe: Recipe) = ShowRecipeFragment().apply {
                //        arguments = Bundle(1).apply {
                //            putSerializable("recipe", recipe)

                val onClick = {recipe: Recipe -> findNavController().navigate(R.id.action_recipesFragment_to_showRecipeFragment, recipeBundle(recipe))}
                recipeAdapter = RecipeAdapter(data.userRecipes?.toMutableList() ?: mutableListOf(), onClick)
                //recipeAdapter = RecipeAdapter(mutableListOf(Recipe("Bread"), Recipe("Pizza")))

                rvRecipeItems.adapter = recipeAdapter
                rvRecipeItems.layoutManager = LinearLayoutManager(context)
            })
        }

    }
}