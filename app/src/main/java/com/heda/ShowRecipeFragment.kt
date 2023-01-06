package com.heda

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.heda.helpers.setToolbarTitle
import com.heda.models.Recipe
import kotlinx.android.synthetic.main.show_recipe.*

class ShowRecipeFragment (
    private val recipe: Recipe
): Fragment(R.layout.show_recipe) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarTitle(requireActivity(), recipe.title)

        tvShowRecipeTitle.text = recipe.title
    }

}