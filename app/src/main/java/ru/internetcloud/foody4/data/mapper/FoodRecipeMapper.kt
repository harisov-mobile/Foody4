package ru.internetcloud.foody4.data.mapper

import ru.internetcloud.foody4.data.database.model.FoodRecipeDbModel
import ru.internetcloud.foody4.data.entity.FoodRecipeWithIngredients
import ru.internetcloud.foody4.data.network.dto.FoodRecipeDTO
import ru.internetcloud.foody4.domain.model.FoodRecipe
import javax.inject.Inject

class FoodRecipeMapper @Inject constructor(
    private val extendedIngredientMapper: ExtendedIngredientMapper
) {

    fun fromDomainToDbModel(foodRecipe: FoodRecipe): FoodRecipeDbModel {
        return FoodRecipeDbModel(
            id = foodRecipe.recipeId,
            title = foodRecipe.title,
            summary = foodRecipe.summary,
            aggregateLikes = foodRecipe.aggregateLikes,
            cheap = foodRecipe.cheap,
            dairyFree = foodRecipe.dairyFree,
            glutenFree = foodRecipe.glutenFree,
            image = foodRecipe.image,
            readyInMinutes = foodRecipe.readyInMinutes,
            sourceName = foodRecipe.sourceName,
            sourceUrl = foodRecipe.sourceUrl,
            vegan = foodRecipe.vegan,
            vegetarian = foodRecipe.vegetarian,
            veryHealthy = foodRecipe.veryHealthy
        )
    }

    fun fromDomainListToDbModelList(list: List<FoodRecipe>): List<FoodRecipeDbModel> {
        return list.map { fromDomainToDbModel(it) }
    }

    fun fromDbModelToDomain(foodRecipeWithIngredients: FoodRecipeWithIngredients): FoodRecipe {
        return FoodRecipe(
            recipeId = foodRecipeWithIngredients.foodRecipeDbModel.id,
            title = foodRecipeWithIngredients.foodRecipeDbModel.title,
            summary = foodRecipeWithIngredients.foodRecipeDbModel.summary,
            aggregateLikes = foodRecipeWithIngredients.foodRecipeDbModel.aggregateLikes,
            cheap = foodRecipeWithIngredients.foodRecipeDbModel.cheap,
            dairyFree = foodRecipeWithIngredients.foodRecipeDbModel.dairyFree,
            extendedIngredients = extendedIngredientMapper.fromDbModelListToDomainList(foodRecipeWithIngredients.extendedIngredients),
            glutenFree = foodRecipeWithIngredients.foodRecipeDbModel.glutenFree,
            image = foodRecipeWithIngredients.foodRecipeDbModel.image,
            readyInMinutes = foodRecipeWithIngredients.foodRecipeDbModel.readyInMinutes,
            sourceName = foodRecipeWithIngredients.foodRecipeDbModel.sourceName,
            sourceUrl = foodRecipeWithIngredients.foodRecipeDbModel.sourceUrl,
            vegan = foodRecipeWithIngredients.foodRecipeDbModel.vegan,
            vegetarian = foodRecipeWithIngredients.foodRecipeDbModel.vegetarian,
            veryHealthy = foodRecipeWithIngredients.foodRecipeDbModel.veryHealthy
        )
    }

    fun fromDbModelListToDomainList(list: List<FoodRecipeWithIngredients>): List<FoodRecipe> {
        return list.map { fromDbModelToDomain(it) }
    }

    fun FromDTOListToDomainList(list: List<FoodRecipeDTO>): List<FoodRecipe> {
        return list.map { fromDTOToDomain(it) }
    }

    fun fromDTOToDomain(foodRecipeDTO: FoodRecipeDTO): FoodRecipe {
        return FoodRecipe(
            recipeId = foodRecipeDTO.recipeId,
            title = foodRecipeDTO.title,
            aggregateLikes = foodRecipeDTO.aggregateLikes,
            cheap = foodRecipeDTO.cheap,
            dairyFree = foodRecipeDTO.dairyFree,
            extendedIngredients = extendedIngredientMapper.fromDTOListToDomainList(foodRecipeDTO.extendedIngredients),
            glutenFree = foodRecipeDTO.glutenFree,
            image = foodRecipeDTO.image ?: "",
            summary = foodRecipeDTO.summary,
            readyInMinutes = foodRecipeDTO.readyInMinutes,
            sourceName = foodRecipeDTO.sourceName,
            sourceUrl = foodRecipeDTO.sourceUrl,
            vegan = foodRecipeDTO.vegan,
            vegetarian = foodRecipeDTO.vegetarian,
            veryHealthy = foodRecipeDTO.veryHealthy
        )
    }
}
