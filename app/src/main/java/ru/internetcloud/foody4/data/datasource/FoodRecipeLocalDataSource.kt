package ru.internetcloud.foody4.data.datasource

import ru.internetcloud.foody4.domain.model.FoodRecipe

interface FoodRecipeLocalDataSource {

    suspend fun getRecipes(): List<FoodRecipe>

    suspend fun insertRecipes(foodRecipeList: List<FoodRecipe>)

    suspend fun deleteRecipes()
}
