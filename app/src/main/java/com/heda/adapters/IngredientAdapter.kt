package com.heda.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heda.R
import com.heda.models.Ingredient
import kotlinx.android.synthetic.main.item_ingredient.view.*

class IngredientAdapter(
    private val ingredients: MutableList<Ingredient>
) : RecyclerView.Adapter<IngredientAdapter.RecipeViewHolder>()  {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val ing = ingredients[position]
        holder.itemView.apply {
            tvIngredientQty.text = ing.qty
            tvIngredientName.text = ing.name
        }
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

}