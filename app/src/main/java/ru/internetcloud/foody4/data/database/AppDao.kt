package ru.internetcloud.foody4.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ru.internetcloud.foody4.data.database.model.FoodRecipeDbModel
import ru.internetcloud.foody4.data.entity.ExtendedIngredientDbModel
import ru.internetcloud.foody4.data.entity.FoodRecipeWithIngredients

@Dao
interface AppDao {

    @Transaction
    @Query("SELECT * FROM food_recipes ORDER BY id DESC")
    suspend fun getAllFoodRecipes(): List<FoodRecipeWithIngredients>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodRecipe(foodRecipeDbModel: FoodRecipeDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodRecipeList(foodRecipeDbModelList: List<FoodRecipeDbModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExtendedIngredientList(extendedIngredientDbModelList: List<ExtendedIngredientDbModel>)

    @Query("DELETE FROM food_recipes")
    suspend fun deleteAllFoodRecipes()

    @Query("DELETE FROM extended_ingredients")
    suspend fun deleteAllExtendedIngredients()
}
