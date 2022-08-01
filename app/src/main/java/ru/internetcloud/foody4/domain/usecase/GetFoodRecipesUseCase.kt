package ru.internetcloud.foody4.domain.usecase

import ru.internetcloud.foody4.domain.model.FoodRecipe
import ru.internetcloud.foody4.domain.model.Result
import ru.internetcloud.foody4.domain.repository.FoodRecipeRepository
import javax.inject.Inject

class GetFoodRecipesUseCase @Inject constructor(
    private val foodRecipeRepository: FoodRecipeRepository
) {

    suspend fun getFoodRecipes(queries: Map<String, String>, loadFromApi: Boolean): Result<List<FoodRecipe>> {
        return foodRecipeRepository.getRecipes(queries, loadFromApi)
    }
}
