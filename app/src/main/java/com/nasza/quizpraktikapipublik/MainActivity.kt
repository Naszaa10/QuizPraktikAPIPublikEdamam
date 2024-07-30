package com.nasza.quizpraktikapipublik

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nasza.quizpraktikapipublik.adapter.RecipeAdapter
import android.widget.Toast
import com.nasza.quizpraktikapipublik.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.Button
import android.widget.EditText
import com.nasza.quizpraktikapipublik.model.Recipe

class MainActivity : AppCompatActivity() {

    private val appId = "dcfab493" //id yang didapat di API
    private val appKey = "2a6a281077af74d7e3e1159a42c9e4aa" //key yang didapat di API juga

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchInput: EditText = findViewById(R.id.search_input)
        val searchButton: Button = findViewById(R.id.search_button)
        val recyclerView: RecyclerView = findViewById(R.id.recipes_recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)

        searchButton.setOnClickListener {
            val query = searchInput.text.toString()
            if (query.isNotBlank()) {
                searchRecipes(query)
            } else {
                Toast.makeText(this, "Please enter a search term", Toast.LENGTH_SHORT).show()
            }
        }

        // Load random recipes on startup
        searchRecipes("random")
    }

    private fun searchRecipes(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiService.searchRecipes(query, appId, appKey)
                if (response.isSuccessful) {
                    val recipes = response.body()?.hits?.map { it.recipe } ?: emptyList()
                    withContext(Dispatchers.Main) {
                        setupRecyclerView(recipes)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupRecyclerView(recipes: List<Recipe>) {
        val adapter = RecipeAdapter(recipes) { recipe ->
            val intent = Intent(this, RecipeDetailActivity::class.java).apply {
                putExtra("RECIPE", recipe)
            }
            startActivity(intent)
        }
        findViewById<RecyclerView>(R.id.recipes_recycler_view).adapter = adapter
    }
}
