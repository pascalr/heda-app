package com.heda

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.heda.view_models.DataViewModel
import kotlinx.android.synthetic.main.recipes_fragment.*


class RecipesFragment : Fragment(R.layout.recipes_fragment) {

    private lateinit var recipeAdapter: RecipeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataViewModel = ViewModelProvider(this)[DataViewModel::class.java]

        dataViewModel.getData { data ->

            val list = data.userRecipes?.map { dataRecipe -> Recipe(dataRecipe.name ?: "") }

            requireActivity().runOnUiThread(Runnable {
                recipeAdapter = RecipeAdapter(list?.toMutableList() ?: mutableListOf())
                //recipeAdapter = RecipeAdapter(mutableListOf(Recipe("Bread"), Recipe("Pizza")))

                rvRecipeItems.adapter = recipeAdapter
                rvRecipeItems.layoutManager = LinearLayoutManager(context)
            })
        }


    }
}