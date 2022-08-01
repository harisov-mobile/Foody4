package ru.internetcloud.foody4.data.entity

import androidx.room.Embedded
import androidx.room.Relation
import ru.internetcloud.foody4.data.database.model.FoodRecipeDbModel

data class FoodRecipeWithIngredients(
    @Embedded
    val foodRecipeDbModel: FoodRecipeDbModel,

    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId",
        entity = ExtendedIngredientDbModel::class
    )
    val extendedIngredients: List<ExtendedIngredientDbModel>
)
