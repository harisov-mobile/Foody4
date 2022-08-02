package ru.internetcloud.foody4.presentation.recipe_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import javax.inject.Inject
import ru.internetcloud.beer.presentation.util.loadImage
import ru.internetcloud.foody4.databinding.ItemRecipeListBinding
import ru.internetcloud.foody4.domain.model.FoodRecipe

class FoodRecipeListAdapter @Inject constructor() : ListAdapter<FoodRecipe, FoodRecipeListViewHolder>(FoodRecipeDiffCallback()) {

    // для отработки нажатий на элемент списка - переменная, которая будет хранить лямбда-функцию,
    // на вход лямбда-функции в качестве параметра будет передан workOrder: WorkOrder,
    // лямбда-функция ничего не возвращает (то есть Unit)
    // а первоначально переменная содержит null
    var onFoodRecipeClickListener: ((foodRecipe: FoodRecipe) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodRecipeListViewHolder {
        var binding = ItemRecipeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodRecipeListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodRecipeListViewHolder, position: Int) {
        val foodRecipe = getItem(position)
        val binding = holder.binding as ItemRecipeListBinding

        binding.titleTextView.text = foodRecipe.title
        binding.descriptionTextView.text = foodRecipe.summary
        binding.clockTextView.text = foodRecipe.readyInMinutes.toString()

        if (foodRecipe.vegan) {
            binding.leafImageView.visibility = View.VISIBLE
            binding.vegetarianTextView.visibility = View.VISIBLE
        } else {
            binding.leafImageView.visibility = View.INVISIBLE
            binding.vegetarianTextView.visibility = View.INVISIBLE
        }

        binding.recipeImageView.loadImage(foodRecipe.image)

        binding.root.setOnClickListener {
            onFoodRecipeClickListener?.invoke(foodRecipe)
        }
    }
}
