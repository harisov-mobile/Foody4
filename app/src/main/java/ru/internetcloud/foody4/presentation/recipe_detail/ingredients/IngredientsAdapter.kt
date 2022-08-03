package ru.internetcloud.foody4.presentation.recipe_detail.ingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.internetcloud.beer.presentation.util.loadImage
import ru.internetcloud.foody4.BuildConfig
import ru.internetcloud.foody4.databinding.IngredientsRowLayoutBinding
import ru.internetcloud.foody4.domain.model.ExtendedIngredient
import java.util.Locale
import javax.inject.Inject

class IngredientsAdapter @Inject constructor() : ListAdapter<ExtendedIngredient, IngredientsViewHolder>(IngredientDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        var binding = IngredientsRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val extendedIngredient = getItem(position)
        val binding = holder.binding as IngredientsRowLayoutBinding

        binding.ingredientName.text = extendedIngredient.name.capitalize(Locale.ROOT)
        binding.ingredientAmount.text = extendedIngredient.amount.toString()
        binding.ingredientUnit.text = extendedIngredient.unit
        binding.ingredientConsistency.text = extendedIngredient.consistency
        binding.ingredientOriginal.text = extendedIngredient.original

        binding.ingredientImageView.loadImage(BuildConfig.SPOONACULAR_BASE_IMAGE_URL + extendedIngredient.image)
    }
}
