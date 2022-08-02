package ru.internetcloud.foody4.presentation.recipe_list

import androidx.recyclerview.widget.DiffUtil
import ru.internetcloud.foody4.domain.model.FoodRecipe

class FoodRecipeDiffCallback : DiffUtil.ItemCallback<FoodRecipe>() {

    override fun areItemsTheSame(oldItem: FoodRecipe, newItem: FoodRecipe): Boolean {
        return oldItem.recipeId == newItem.recipeId
    }

    override fun areContentsTheSame(oldItem: FoodRecipe, newItem: FoodRecipe): Boolean {
        return oldItem == newItem
    }
}
