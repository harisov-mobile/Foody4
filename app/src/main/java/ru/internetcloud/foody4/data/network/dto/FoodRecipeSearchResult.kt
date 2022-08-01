package ru.internetcloud.foody4.data.network.dto

import com.google.gson.annotations.SerializedName

data class FoodRecipeSearchResult(
    @SerializedName("results")
    val recipes: List<FoodRecipeDTO>
)
