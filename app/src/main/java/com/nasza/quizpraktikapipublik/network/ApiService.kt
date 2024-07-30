package com.nasza.quizpraktikapipublik.network

import com.nasza.quizpraktikapipublik.model.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    suspend fun searchRecipes(
        @Query("q") query: String,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String
    ): Response<RecipeResponse>
}

