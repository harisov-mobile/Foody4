package ru.internetcloud.foody4.presentation.recipe_list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.ViewModelProvider
import ru.internetcloud.foody4.FoodyApp
import ru.internetcloud.foody4.R
import ru.internetcloud.foody4.databinding.FragmentRecipeListBinding
import ru.internetcloud.foody4.di.ViewModelFactory
import ru.internetcloud.foody4.domain.model.FoodFilter
import ru.internetcloud.foody4.domain.model.FoodRecipe
import ru.internetcloud.foody4.domain.model.Result
import ru.internetcloud.foody4.presentation.bottom_sheet.RecipesBottomSheetFragment
import javax.inject.Inject

class FoodRecipeListFragment : Fragment(), FragmentResultListener {

    interface Callbacks {
        fun onFoodRecipeItemClick(foodRecipe: FoodRecipe)
    }

    // даггер:
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var foodRecipeListAdapter: FoodRecipeListAdapter

    private val component by lazy {
        (requireActivity().application as FoodyApp).component
    }

    private lateinit var foodRecipeListViewModel: FoodRecipeListViewModel

    private var _binding: FragmentRecipeListBinding? = null
    val binding: FragmentRecipeListBinding
        get() = _binding ?: throw IllegalStateException("FragmentRecipeListBinding is null")

    companion object {
        private val REQUEST_FOOD_FILTER_KEY = "food_filter_key"
        private val ANSWER_KEY = "answer"
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("rustam", " onViewCreated - 1 - $this")

        foodRecipeListViewModel = ViewModelProvider(this, viewModelFactory).get(FoodRecipeListViewModel::class.java)

        setupFoodRecipeRecyclerView()
        setupClickListeners()
        observeViewModel()
        setupFragmentResultListeners()

        Log.i("rustam", " onViewCreated - 2 - $this")
    }

    private fun setupFoodRecipeRecyclerView() {
        binding.foodRecipeRecyclerView.adapter = foodRecipeListAdapter
        // в XML  прописано app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
    }

    private fun setupClickListeners() {
        foodRecipeListAdapter.onFoodRecipeClickListener = { foodRecipe ->
            // parentFragment?.onFoodRecipeItemClick(foodRecipe)

            val parentFr = getParentFragment()
            parentFr?.let {
                (it as Callbacks).onFoodRecipeItemClick(foodRecipe)
            }
        }

        binding.tryAgainButton.setOnClickListener {
            foodRecipeListViewModel.fetchFoodRecipes(loadFromApi = true)
        }

        binding.floatingActionButton.setOnClickListener {

            RecipesBottomSheetFragment
                .newInstance(foodRecipeListViewModel.foodFilter.copy(), REQUEST_FOOD_FILTER_KEY, ANSWER_KEY)
                .show(childFragmentManager, REQUEST_FOOD_FILTER_KEY)
        }
    }

    private fun observeViewModel() {

        foodRecipeListViewModel.result.observe(viewLifecycleOwner) { currentResult ->

            binding.errorImageView.visibility = View.GONE
            binding.errorTextView.visibility = View.GONE
            binding.tryAgainButton.visibility = View.GONE

            when (currentResult) {
                is Result.Loading -> {
                    binding.foodRecipeRecyclerView.visibility = View.INVISIBLE
                    binding.shimmerFrameLayout.visibility = View.VISIBLE
                    foodRecipeListAdapter.submitList(emptyList())
                }

                is Result.Error -> {
                    binding.shimmerFrameLayout.visibility = View.GONE
                    binding.errorTextView.text = getString(
                        R.string.error_message, currentResult.exception.message.toString()
                    )

                    binding.errorImageView.visibility = View.VISIBLE
                    binding.errorTextView.visibility = View.VISIBLE
                    binding.tryAgainButton.visibility = View.VISIBLE
                }

                is Result.Success -> {
                    if (currentResult.data.size == 0) {
                        binding.shimmerFrameLayout.visibility = View.GONE
                        binding.errorTextView.visibility = View.VISIBLE
                    } else {
                        val kol = currentResult.data.size
                        for (i in 0 until kol) {
                            // Log.i("rustam", "recipeId = ${currentResult.data.get(i).recipeId}")
                        }
                        // Log.i("rustam", "--------------------------")

                        foodRecipeListAdapter.submitList(currentResult.data)
                        binding.shimmerFrameLayout.visibility = View.GONE
                        binding.foodRecipeRecyclerView.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun setupFragmentResultListeners() {
        childFragmentManager.setFragmentResultListener(REQUEST_FOOD_FILTER_KEY, viewLifecycleOwner, this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onFragmentResult(requestKey: String, result: Bundle) {
        when (requestKey) {
            REQUEST_FOOD_FILTER_KEY -> {
                val filter = result.getParcelable<FoodFilter>(ANSWER_KEY)
                filter?.let { newFoodFilter ->
                    if (!newFoodFilter.mealTypeTag.equals(foodRecipeListViewModel.foodFilter.mealTypeTag) ||
                        !newFoodFilter.dietTypeTag.equals(foodRecipeListViewModel.foodFilter.dietTypeTag)
                    ) {
                        foodRecipeListViewModel.foodFilter = newFoodFilter
                        foodRecipeListViewModel.fetchFoodRecipes(loadFromApi = true)
                        foodRecipeListViewModel.saveFoodFilter(requireActivity().application, foodRecipeListViewModel.foodFilter)
                    }
                }
            }
        }
    }
}
