package ru.internetcloud.foody4.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_recipes")
data class FoodRecipeDbModel(
    @PrimaryKey
    val id: Int,
    val title: String,
    val summary: String,
    val aggregateLikes: Int,
    val cheap: Boolean,
    val dairyFree: Boolean,
    val glutenFree: Boolean,
    val image: String,
    val readyInMinutes: Int,
    val sourceName: String?,
    val sourceUrl: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean
)
