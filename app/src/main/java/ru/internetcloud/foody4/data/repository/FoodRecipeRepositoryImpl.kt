package ru.internetcloud.foody4.data.repository

import ru.internetcloud.foody4.data.datasource.FoodRecipeLocalDataSource
import ru.internetcloud.foody4.data.datasource.FoodRecipeRemoteDataSource
import ru.internetcloud.foody4.domain.model.FoodRecipe
import ru.internetcloud.foody4.domain.model.Result
import ru.internetcloud.foody4.domain.repository.FoodRecipeRepository
import javax.inject.Inject

class FoodRecipeRepositoryImpl @Inject constructor(
    private val foodRecipeRemoteDatasource: FoodRecipeRemoteDataSource,
    private val foodRecipeLocalDataSource: FoodRecipeLocalDataSource
) : FoodRecipeRepository {

    override suspend fun getRecipes(queries: Map<String, String>, loadFromApi: Boolean): Result<List<FoodRecipe>> {

        if (loadFromApi) {
            foodRecipeLocalDataSource.deleteRecipes()
            return getFromApi(queries)
        } else {
            val foodRecipeList = foodRecipeLocalDataSource.getRecipes()
            if (foodRecipeList.isEmpty()) {
                return getFromApi(queries)
            } else {
                return Result.Success(foodRecipeList)
            }
        }
    }

    suspend fun getFromApi(queries: Map<String, String>): Result<List<FoodRecipe>> {
        val apiResult = foodRecipeRemoteDatasource.getRecipes(queries)
        if (apiResult is Result.Success) {
            apiResult.data = apiResult.data.sortedByDescending { it.recipeId }
            foodRecipeLocalDataSource.insertRecipes(apiResult.data)
        }
        return apiResult
    }
}
