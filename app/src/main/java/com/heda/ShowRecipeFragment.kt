package com.heda

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.heda.adapters.IngredientAdapter
import com.heda.helpers.parseIngredients
import com.heda.helpers.setToolbarTitle
import com.heda.models.Recipe
import com.heda.view_models.DataViewModel
import kotlinx.android.synthetic.main.recipes_fragment.*
import kotlinx.android.synthetic.main.show_recipe.*

class ShowRecipeFragment (
    private val recipe: Recipe
): Fragment(R.layout.show_recipe) {

    private lateinit var ingredientAdapter: IngredientAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarTitle(requireActivity(), recipe.name ?: "")

        tvShowRecipeTitle.text = recipe.name

        val dataViewModel = ViewModelProvider(this)[DataViewModel::class.java]

        dataViewModel.getData { data ->

            val recipe = data.userRecipes?.find { r -> r.id == recipe.id }

            val ings = parseIngredients(recipe?.ingredients)
            if (ings != null) {
                requireActivity().runOnUiThread(Runnable {
                    ingredientAdapter = IngredientAdapter(ings.toMutableList())

                    rvIngredients.adapter = ingredientAdapter
                    rvIngredients.layoutManager = LinearLayoutManager(context)
                })
            }
        }
    }

}