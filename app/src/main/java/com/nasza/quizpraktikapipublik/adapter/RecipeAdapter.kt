package com.nasza.quizpraktikapipublik.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nasza.quizpraktikapipublik.R
import com.nasza.quizpraktikapipublik.model.Recipe

class RecipeAdapter(private val recipes: List<Recipe>, private val onClick: (Recipe) -> Unit) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe, onClick)
    }

    override fun getItemCount(): Int = recipes.size

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeLabel: TextView = itemView.findViewById(R.id.recipe_label)
        private val recipeImage: ImageView = itemView.findViewById(R.id.recipe_image)

        fun bind(recipe: Recipe, onClick: (Recipe) -> Unit) {
            recipeLabel.text = recipe.label
            Glide.with(itemView).load(recipe.image).into(recipeImage)
            itemView.setOnClickListener { onClick(recipe) }
        }
    }
}
