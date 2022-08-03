package ru.internetcloud.foody4.presentation.recipe_detail.overview

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ru.internetcloud.beer.presentation.util.loadImage
import ru.internetcloud.foody4.R
import ru.internetcloud.foody4.databinding.FragmentOverviewBinding
import ru.internetcloud.foody4.domain.model.FoodRecipe

class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    companion object {

        private const val FOOD_RECIPE_KEY = "food_recipe_key"

        fun newInstance(foodRecipe: FoodRecipe): OverviewFragment {
            return OverviewFragment().apply {
                val args = Bundle()
                args.putParcelable(FOOD_RECIPE_KEY, foodRecipe)
                arguments = args
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.i("rustam", " onAttach - $this")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("rustam", " onCreate - $this")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("rustam", " onViewCreated - 1 - $this")

        arguments?.let { args ->
            val currentFoodRecipe = args.getParcelable<FoodRecipe>(FOOD_RECIPE_KEY)

            currentFoodRecipe?.let { foodRecipe ->
                binding.mainImageView.loadImage(foodRecipe.image)
                binding.titleTextView.text = foodRecipe.title
                binding.likesTextView.text = foodRecipe.aggregateLikes.toString()
                binding.timeTextView.text = foodRecipe.readyInMinutes.toString()
                binding.summaryTextView.text = foodRecipe.summary

                updateColors(foodRecipe.vegetarian, binding.vegetarianTextView, binding.vegetarianImageView)
                updateColors(foodRecipe.vegan, binding.veganTextView, binding.veganImageView)
                updateColors(foodRecipe.cheap, binding.cheapTextView, binding.cheapImageView)
                updateColors(foodRecipe.dairyFree, binding.dairyFreeTextView, binding.dairyFreeImageView)
                updateColors(foodRecipe.glutenFree, binding.glutenFreeTextView, binding.glutenFreeImageView)
                updateColors(foodRecipe.veryHealthy, binding.healthyTextView, binding.healthyImageView)
            }
        }

        Log.i("rustam", " onViewCreated - 2 - $this")
    }

    private fun updateColors(stateIsOn: Boolean, textView: TextView, imageView: ImageView) {
        if (stateIsOn) {
            imageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        Log.i("rustam", " onDestroyView - $this")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i("rustam", " onDestroy - $this")
    }
}
