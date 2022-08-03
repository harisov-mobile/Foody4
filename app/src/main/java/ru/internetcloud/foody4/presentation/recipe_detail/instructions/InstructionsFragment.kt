package ru.internetcloud.foody4.presentation.recipe_detail.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import ru.internetcloud.foody4.databinding.FragmentInstructionsBinding
import ru.internetcloud.foody4.domain.model.FoodRecipe

class InstructionsFragment : Fragment() {

    private var _binding: FragmentInstructionsBinding? = null
    private val binding get() = _binding!!

    companion object {

        private const val FOOD_RECIPE_KEY = "food_recipe_key"

        fun newInstance(foodRecipe: FoodRecipe): InstructionsFragment {
            return InstructionsFragment().apply {
                val args = Bundle()
                args.putParcelable(FOOD_RECIPE_KEY, foodRecipe)
                arguments = args
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInstructionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { args ->
            val currentFoodRecipe = args.getParcelable<FoodRecipe>(FOOD_RECIPE_KEY)

            currentFoodRecipe?.let { foodRecipe ->
                binding.instructionsWebView.webViewClient = object : WebViewClient() {}
                val websiteUrl: String = foodRecipe.sourceUrl
                binding.instructionsWebView.loadUrl(websiteUrl)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
