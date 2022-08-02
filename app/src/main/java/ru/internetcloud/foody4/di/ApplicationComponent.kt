package ru.internetcloud.foody4.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.internetcloud.foody4.presentation.bottom_sheet.RecipesBottomSheetFragment
import ru.internetcloud.foody4.presentation.recipe_list.FoodRecipeListFragment

@Component(modules = [DataModule::class, ViewModelModule::class])
@ApplicationScope
interface ApplicationComponent {

    fun inject(fragment: FoodRecipeListFragment)

    fun inject(fragment: RecipesBottomSheetFragment)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}
