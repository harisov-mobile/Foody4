package ru.internetcloud.foody4.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
@ApplicationScope
interface ApplicationComponent {

//    fun inject(fragment: FoodRecipeListFragment)
//
//    fun inject(fragment: RecipesBottomSheetFragment)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}
