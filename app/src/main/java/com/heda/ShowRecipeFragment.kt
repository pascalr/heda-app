package com.heda

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.heda.adapters.IngredientAdapter
import com.heda.helpers.loadImage
import com.heda.helpers.parseIngredients
import com.heda.helpers.parseInstructions
import com.heda.helpers.setToolbarTitle
import com.heda.models.Recipe
import com.heda.view_models.DataViewModel
import kotlinx.android.synthetic.main.recipes_fragment.*
import kotlinx.android.synthetic.main.show_recipe.*

class ShowRecipeFragment: Fragment(R.layout.show_recipe) {

    private lateinit var ingredientAdapter: IngredientAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipe: Recipe = arguments?.getSerializable("recipe") as Recipe

        setToolbarTitle(requireActivity(), recipe.name ?: "")

        tvShowRecipeTitle.text = recipe.name

        val dataViewModel = ViewModelProvider(this)[DataViewModel::class.java]

        val rootDir = requireActivity().getExternalFilesDir(null)
        dataViewModel.getData(rootDir) { data ->

            val recipe = data.userRecipes?.find { r -> r.id == recipe.id }

            if (recipe != null) {
                val ings = parseIngredients(recipe.ingredients)
                requireActivity().runOnUiThread(Runnable {

                    //tvRecipeInstructions.text = parseInstructions(recipe?.json ?: "")
                    val htmlDocument = "<html><body><h1>Test Content</h1><p>Testing, testing, testing...</p></body></html>"
                    wvInstructions.loadDataWithBaseURL(null, htmlDocument, "text/HTML", "UTF-8", null)

                    //ivShowRecipeImage.setIm

                    loadImage(recipe.image_slug, ivShowRecipeImage)

                    btnRecipeWebsiteLink.setOnClickListener {
                        val openURL = Intent(android.content.Intent.ACTION_VIEW)
                        openURL.data = Uri.parse("https://www.hedacuisine.com/r/${recipe.id}")
                        startActivity(openURL)
                    }

                    if (ings != null) {
                        ingredientAdapter = IngredientAdapter(ings.toMutableList())

                        rvIngredients.adapter = ingredientAdapter
                        rvIngredients.layoutManager = LinearLayoutManager(context)
                    }
                })
            }
        }
    }

    //companion object {
    //    fun newInstance(recipe: Recipe) = ShowRecipeFragment().apply {
    //        arguments = Bundle(1).apply {
    //            putSerializable("recipe", recipe)
    //        }
    //    }
    //}

}

fun recipeBundle(recipe: Recipe) : Bundle {
    val bundle = Bundle()
    bundle.putSerializable("recipe", recipe)
    return bundle
}