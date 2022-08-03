package ru.internetcloud.foody4.presentation.recipe_detail.ingredients

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.internetcloud.foody4.FoodyApp
import ru.internetcloud.foody4.databinding.FragmentIngredientsBinding
import ru.internetcloud.foody4.domain.model.FoodRecipe
import javax.inject.Inject

class IngredientsFragment : Fragment() {

    @Inject
    lateinit var ingredientsAdapter: IngredientsAdapter

    private val component by lazy {
        (requireActivity().application as FoodyApp).component
    }

    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!

    companion object {

        private const val FOOD_RECIPE_KEY = "food_recipe_key"

        fun newInstance(foodRecipe: FoodRecipe): IngredientsFragment {
            return IngredientsFragment().apply {
                val args = Bundle()
                args.putParcelable(FOOD_RECIPE_KEY, foodRecipe)
                arguments = args
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.i("rustam", " onAttach - $this")

        // даггер:
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("rustam", " onCreate - $this")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Log.i("rustam", " onCreateView - $this")

        // Inflate the layout for this fragment
        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("rustam", " onViewCreated - 1 - $this")

        setupRecyclerView()

        arguments?.let { args ->
            val currentFoodRecipe = args.getParcelable<FoodRecipe>(FOOD_RECIPE_KEY)

            currentFoodRecipe?.let { foodRecipe ->
                ingredientsAdapter.submitList(foodRecipe.extendedIngredients)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.ingredientsRecyclerview.adapter = ingredientsAdapter
        binding.ingredientsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
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
