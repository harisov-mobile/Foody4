package ru.internetcloud.foody4.data.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import ru.internetcloud.foody4.data.network.dto.FoodRecipeSearchResult

interface FoodRecipeApiService {

    @GET("recipes/complexSearch")
    suspend fun getRecipeResponse(
        @QueryMap
        queries: Map<String, String>
    ): Response<FoodRecipeSearchResult>
}
