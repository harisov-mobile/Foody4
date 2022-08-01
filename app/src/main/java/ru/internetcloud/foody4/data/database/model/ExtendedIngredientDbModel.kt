package ru.internetcloud.foody4.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "extended_ingredients")
data class ExtendedIngredientDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val recipeId: Int,
    val amount: Double,
    val consistency: String,
    val image: String,
    val name: String,
    val original: String,
    val unit: String
)
