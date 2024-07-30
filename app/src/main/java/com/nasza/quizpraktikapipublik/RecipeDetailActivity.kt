package com.nasza.quizpraktikapipublik

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nasza.quizpraktikapipublik.model.Recipe
import com.bumptech.glide.Glide

class RecipeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        val recipe = intent.getParcelableExtra<Recipe>("RECIPE")

        val recipeLabel: TextView = findViewById(R.id.recipe_detail_label)
        val recipeImage: ImageView = findViewById(R.id.recipe_detail_image)
        val ingredientsTextView: TextView = findViewById(R.id.ingredients)

        recipe?.let {
            recipeLabel.text = it.label
            Glide.with(this).load(it.image).into(recipeImage)
            ingredientsTextView.text = it.ingredientLines.joinToString("\n")
        }
    }
}
