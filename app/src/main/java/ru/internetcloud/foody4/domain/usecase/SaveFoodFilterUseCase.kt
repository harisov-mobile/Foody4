package ru.internetcloud.foody4.domain.usecase

import android.content.Context
import ru.internetcloud.foody4.domain.model.FoodFilter
import ru.internetcloud.foody4.domain.repository.FoodFilterRepository
import javax.inject.Inject

class SaveFoodFilterUseCase @Inject constructor(
    private val foodFilterRepository: FoodFilterRepository
) {
    fun saveFoodFilter(context: Context, foodFilter: FoodFilter) {
        return foodFilterRepository.saveFoodFilter(context, foodFilter)
    }
}
