package ru.internetcloud.foody4.domain.repository

import android.content.Context
import ru.internetcloud.foody4.domain.model.FoodFilter

interface FoodFilterRepository {

    fun readFoodFilter(context: Context): FoodFilter

    fun saveFoodFilter(context: Context, foodFilter: FoodFilter)
}
