package ru.internetcloud.foody4.data.preference

import android.content.Context
import android.preference.PreferenceManager
import ru.internetcloud.foody4.domain.model.FoodFilter

private const val MEAL_TYPE = "meal_type"
private const val DIET_TYPE = "diet_type"

private const val DEFAULT_MEAL_TYPE_TAG = "main_course_chip"
private const val DEFAULT_DIET_TYPE_TAG = "gluten_free_chip"

object FoodFilterPreferences {

    fun setStoredFoodFilter(context: Context, foodFilter: FoodFilter) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(MEAL_TYPE, foodFilter.mealTypeTag)
            .putString(DIET_TYPE, foodFilter.dietTypeTag)
            .apply()
    }

    fun getStoredFoodFilter(context: Context): FoodFilter {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)

        val selectedMealType = prefs.getString(MEAL_TYPE, DEFAULT_MEAL_TYPE_TAG)!!
        val selectedDietType = prefs.getString(DIET_TYPE, DEFAULT_DIET_TYPE_TAG)!!
        return FoodFilter(
            mealTypeTag = selectedMealType,
            dietTypeTag = selectedDietType
        )
    }
}
