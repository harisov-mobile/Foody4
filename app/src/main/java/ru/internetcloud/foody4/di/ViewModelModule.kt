package ru.internetcloud.foody4.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.internetcloud.foody4.presentation.bottom_sheet.RecipesBottomSheetViewModel
import ru.internetcloud.foody4.presentation.recipe_list.FoodRecipeListViewModel

@Module
interface ViewModelModule {

    // перечислить тут все вью-модели
    @IntoMap
    @ViewModelKey(FoodRecipeListViewModel::class)
    @Binds
    fun bindFoodRecipeListViewModel(impl: FoodRecipeListViewModel): ViewModel

    @IntoMap
    @ViewModelKey(RecipesBottomSheetViewModel::class)
    @Binds
    fun bindRecipesBottomSheetViewModel(impl: RecipesBottomSheetViewModel): ViewModel
}
