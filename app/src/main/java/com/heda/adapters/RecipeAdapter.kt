package com.heda.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heda.R
import com.heda.helpers.loadImage
import com.heda.models.Recipe
import kotlinx.android.synthetic.main.recipe_item.view.*
import kotlinx.android.synthetic.main.show_recipe.*

class RecipeAdapter(
    private val recipes: MutableList<Recipe>,
    private val onClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>()  {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false))
    }

    fun addTodo(todo: Recipe) {
        recipes.add(todo)
        notifyItemInserted(recipes.size - 1)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.itemView.apply {
            tvRecipeTitle.text = recipe.name
            tvRecipeTitle.setOnClickListener {
                onClick(recipe)
            }
        }
        loadImage(recipe.image_slug, holder.itemView.ivRecipeImage)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

}