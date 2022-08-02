package ru.internetcloud.foody4.presentation.bottom_sheet

import androidx.lifecycle.ViewModel
import ru.internetcloud.foody4.domain.model.FoodFilter
import javax.inject.Inject

class RecipesBottomSheetViewModel @Inject constructor() : ViewModel() {

    var selectedFoodFilter: FoodFilter? = null
    lateinit var requestKey: String
    lateinit var argName: String
}
