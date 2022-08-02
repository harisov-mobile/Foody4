package ru.internetcloud.foody4.presentation.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import ru.internetcloud.foody4.FoodyApp
import ru.internetcloud.foody4.databinding.FragmentRecipesBottomSheetBinding
import ru.internetcloud.foody4.di.ViewModelFactory
import ru.internetcloud.foody4.domain.model.DietChipTag
import ru.internetcloud.foody4.domain.model.FoodFilter
import ru.internetcloud.foody4.domain.model.MealChipTag
import javax.inject.Inject

class RecipesBottomSheetFragment : BottomSheetDialogFragment() {

    // даггер:
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as FoodyApp).component
    }

    private lateinit var viewModel: RecipesBottomSheetViewModel

    private var _binding: FragmentRecipesBottomSheetBinding? = null
    private val binding: FragmentRecipesBottomSheetBinding
        get() = _binding ?: throw IllegalStateException("Error FragmentRecipesBottomSheetBinding is NULL")

    companion object {

        private const val FOOD_FILTER = "food_filter"
        private const val PARENT_REQUEST_KEY = "parent_request_picker_key"
        private const val PARENT_ARG_NAME = "parent_arg_name"

        fun newInstance(
            foodFilter: FoodFilter,
            parentRequestKey: String,
            parentArgName: String
        ): RecipesBottomSheetFragment {
            val args = Bundle().apply {
                putParcelable(FOOD_FILTER, foodFilter)
                putString(PARENT_REQUEST_KEY, parentRequestKey)
                putString(PARENT_ARG_NAME, parentArgName)
            }
            return RecipesBottomSheetFragment().apply {
                arguments = args
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRecipesBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // даггер:
        component.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(RecipesBottomSheetViewModel::class.java)

        viewModel.selectedFoodFilter ?: let {
            // это новый экран, т.к. viewModel.selectedFoodFilter не проинициализирована
            arguments?.let { arg ->
                viewModel.selectedFoodFilter = arg.getParcelable(FOOD_FILTER)
                viewModel.requestKey = arg.getString(PARENT_REQUEST_KEY, "")
                viewModel.argName = arg.getString(PARENT_ARG_NAME, "")
            }
        }

        viewModel.selectedFoodFilter?.let { foodFilter ->
            // при повороте экрана - надо заново отобразить
            updateUI(foodFilter)
        }

        setupClickListeners()
    }

    private fun updateUI(filter: FoodFilter) {

        updateChip(filter.mealTypeTag, binding.mealTypeChipGroup)
        updateChip(filter.dietTypeTag, binding.dietTypeChipGroup)
    }

    private fun updateChip(typeTag: String, chipGroup: ChipGroup) {

        val chip = chipGroup.findViewWithTag<Chip>(typeTag)
        chip?.let {
            it.isChecked = true
            chipGroup.requestChildFocus(chip, chip)
        }
    }

    private fun setupClickListeners() {
        binding.applyButton.setOnClickListener {
            sendResultToFragment(viewModel.selectedFoodFilter ?: throw IllegalStateException("foodFilter is NULL"))
            dismiss()
        }

        binding.mealTypeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            viewModel.selectedFoodFilter?.mealTypeTag = MealChipTag.valueOf(chip.getTag() as String).chipName
        }

        binding.dietTypeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            viewModel.selectedFoodFilter?.dietTypeTag = DietChipTag.valueOf(chip.getTag() as String).chipName
        }
    }

    private fun sendResultToFragment(result: FoodFilter) {
        val bundle = Bundle().apply {
            putParcelable(viewModel.argName, result)
        }
        setFragmentResult(viewModel.requestKey, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
