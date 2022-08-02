package ru.internetcloud.foody4.presentation.recipe_list

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.internetcloud.foody4.BuildConfig
import ru.internetcloud.foody4.domain.model.FoodFilter
import ru.internetcloud.foody4.domain.model.FoodRecipe
import ru.internetcloud.foody4.domain.model.Result
import ru.internetcloud.foody4.domain.usecase.GetFoodRecipesUseCase
import ru.internetcloud.foody4.domain.usecase.ReadFoodFilterUseCase
import ru.internetcloud.foody4.domain.usecase.SaveFoodFilterUseCase
import javax.inject.Inject

class FoodRecipeListViewModel @Inject constructor(
    private val getFoodRecipesUseCase: GetFoodRecipesUseCase,
    private val saveFoodFilterUseCase: SaveFoodFilterUseCase,
    readFoodFilterUseCase: ReadFoodFilterUseCase,
    application: Application
) : ViewModel() {

    private val _result = MutableLiveData<Result<List<FoodRecipe>>>()
    val result: LiveData<Result<List<FoodRecipe>>>
        get() = _result

    var foodFilter: FoodFilter = readFoodFilterUseCase.readFoodFilter(application)

    init {
        Log.i("rustam", "init")
        fetchFoodRecipes(loadFromApi = false)
    }

    fun fetchFoodRecipes(loadFromApi: Boolean) {
        viewModelScope.launch {
            _result.value = Result.Loading
            delay(3000)

            val queries = getQueryMap()

            _result.value = getFoodRecipesUseCase.getFoodRecipes(queries, loadFromApi)
        }
    }

    fun getQueryMap(): HashMap<String, String> {
        val queryMap: HashMap<String, String> = HashMap()
        queryMap["number"] = "5" // кол-во рецептов, которые мы готовы принять (от 1 до 100)
        queryMap["apiKey"] = BuildConfig.SPOONACULAR_API_KEY
        queryMap["type"] = foodFilter.mealTypeTag
        queryMap["diet"] = foodFilter.dietTypeTag
        queryMap["addRecipeInformation"] = "true"
        queryMap["fillIngredients"] = "true"

        Log.i("rustam", "foodFilter.mealType = ${foodFilter.mealTypeTag}, foodFilter.dietType = ${foodFilter.dietTypeTag}")

        return queryMap
    }

    fun saveFoodFilter(context: Context, foodFilter: FoodFilter) {
        saveFoodFilterUseCase.saveFoodFilter(context, foodFilter)
    }
}
