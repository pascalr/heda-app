package com.heda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.heda.databinding.RecipesFragmentBinding
import com.heda.view_models.DataViewModel
import kotlinx.android.synthetic.main.recipes_fragment.*

class RecipesFragment : Fragment(R.layout.recipes_fragment) {

    private lateinit var recipeAdapter: RecipeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val fetchDataViewModel = ViewModelProvider(this)[DataViewModel::class.java]

        recipeAdapter = RecipeAdapter(mutableListOf(Recipe("Bread"), Recipe("Pizza")))

        rvRecipeItems.adapter = recipeAdapter
        rvRecipeItems.layoutManager = LinearLayoutManager(context)
    }
}