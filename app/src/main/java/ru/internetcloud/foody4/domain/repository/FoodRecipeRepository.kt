package ru.internetcloud.foody4.domain.repository

import ru.internetcloud.foody4.domain.model.FoodRecipe
import ru.internetcloud.foody4.domain.model.Result

interface FoodRecipeRepository {

    suspend fun getRecipes(queries: Map<String, String>, loadFromApi: Boolean): Result<List<FoodRecipe>>
}
