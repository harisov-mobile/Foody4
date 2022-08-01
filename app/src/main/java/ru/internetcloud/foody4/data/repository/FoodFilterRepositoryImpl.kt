package ru.internetcloud.foody4.data.repository

import android.content.Context
import ru.internetcloud.foody4.data.preference.FoodFilterPreferences
import ru.internetcloud.foody4.domain.model.FoodFilter
import ru.internetcloud.foody4.domain.repository.FoodFilterRepository
import javax.inject.Inject

class FoodFilterRepositoryImpl @Inject constructor() : FoodFilterRepository {

    override fun readFoodFilter(context: Context): FoodFilter {
        return FoodFilterPreferences.getStoredFoodFilter(context)
    }

    override fun saveFoodFilter(context: Context, foodFilter: FoodFilter) {
        FoodFilterPreferences.setStoredFoodFilter(context, foodFilter)
    }
}
