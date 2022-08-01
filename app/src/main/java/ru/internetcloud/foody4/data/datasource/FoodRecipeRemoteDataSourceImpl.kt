package ru.internetcloud.foody4.data.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.internetcloud.foody4.data.mapper.FoodRecipeMapper
import ru.internetcloud.foody4.data.network.api.FoodRecipeApiService
import ru.internetcloud.foody4.domain.model.FoodRecipe
import ru.internetcloud.foody4.domain.model.Result
import javax.inject.Inject

class FoodRecipeRemoteDataSourceImpl @Inject constructor(
    private val recipeApiService: FoodRecipeApiService,
    private val mapper: FoodRecipeMapper
) : FoodRecipeRemoteDataSource {

    override suspend fun getRecipes(queries: Map<String, String>): Result<List<FoodRecipe>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = recipeApiService.getRecipeResponse(queries)
                if (response.isSuccessful) {
                    response.body()?.let { recipeApiResponse ->
                        Result.Success<List<FoodRecipe>>(mapper.FromDTOListToDomainList(recipeApiResponse.recipes))
                    } ?: let {
                        Result.Success<List<FoodRecipe>>(emptyList())
                    }
                } else {
                    Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
}
