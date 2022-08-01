package ru.internetcloud.foody4.domain.usecase

import android.content.Context
import ru.internetcloud.foody4.domain.model.FoodFilter
import ru.internetcloud.foody4.domain.repository.FoodFilterRepository
import javax.inject.Inject

class ReadFoodFilterUseCase @Inject constructor(
    private val foodFilterRepository: FoodFilterRepository
) {
    fun readFoodFilter(context: Context): FoodFilter {
        return foodFilterRepository.readFoodFilter(context)
    }
}
