package ru.internetcloud.foody4.presentation.recipe_detail.ingredients

import androidx.recyclerview.widget.DiffUtil
import ru.internetcloud.foody4.domain.model.ExtendedIngredient

class IngredientDiffCallback : DiffUtil.ItemCallback<ExtendedIngredient>() {

    override fun areItemsTheSame(oldItem: ExtendedIngredient, newItem: ExtendedIngredient): Boolean {
        return oldItem.name.equals(newItem.name)
    }

    override fun areContentsTheSame(oldItem: ExtendedIngredient, newItem: ExtendedIngredient): Boolean {
        return oldItem == newItem
    }
}
