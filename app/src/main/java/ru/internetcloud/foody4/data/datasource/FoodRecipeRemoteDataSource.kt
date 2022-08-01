package ru.internetcloud.foody4.data.datasource

import ru.internetcloud.foody4.domain.model.FoodRecipe
import ru.internetcloud.foody4.domain.model.Result

interface FoodRecipeRemoteDataSource {

    suspend fun getRecipes(queries: Map<String, String>): Result<List<FoodRecipe>>
}
