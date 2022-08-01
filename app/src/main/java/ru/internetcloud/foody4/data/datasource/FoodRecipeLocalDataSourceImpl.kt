package ru.internetcloud.foody4.data.datasource

import ru.internetcloud.foody4.data.database.AppDao
import ru.internetcloud.foody4.data.mapper.ExtendedIngredientMapper
import ru.internetcloud.foody4.data.mapper.FoodRecipeMapper
import ru.internetcloud.foody4.domain.model.FoodRecipe
import javax.inject.Inject

class FoodRecipeLocalDataSourceImpl @Inject constructor(
    private val appDao: AppDao,
    private val foodRecipeMapper: FoodRecipeMapper,
    private val extendedIngredientDbMapper: ExtendedIngredientMapper
) : FoodRecipeLocalDataSource {

    override suspend fun getRecipes(): List<FoodRecipe> {
        return foodRecipeMapper.fromDbModelListToDomainList(appDao.getAllFoodRecipes())
    }

    override suspend fun insertRecipes(foodRecipeList: List<FoodRecipe>) {
        appDao.insertFoodRecipeList(foodRecipeMapper.fromDomainListToDbModelList(foodRecipeList))

        for (currentFoodRecipe in foodRecipeList) {
            appDao.insertExtendedIngredientList(
                extendedIngredientDbMapper.fromDomainListToDbModelList(
                    currentFoodRecipe.extendedIngredients,
                    currentFoodRecipe.recipeId
                )
            )
        }
    }

    override suspend fun deleteRecipes() {
        appDao.deleteAllExtendedIngredients()
        appDao.deleteAllFoodRecipes()
    }
}
