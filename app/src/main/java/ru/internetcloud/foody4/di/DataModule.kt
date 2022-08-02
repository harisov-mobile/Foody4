package ru.internetcloud.foody4.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.internetcloud.foody4.data.database.AppDao
import ru.internetcloud.foody4.data.database.AppDatabase
import ru.internetcloud.foody4.data.datasource.FoodRecipeLocalDataSource
import ru.internetcloud.foody4.data.datasource.FoodRecipeLocalDataSourceImpl
import ru.internetcloud.foody4.data.datasource.FoodRecipeRemoteDataSource
import ru.internetcloud.foody4.data.datasource.FoodRecipeRemoteDataSourceImpl
import ru.internetcloud.foody4.data.network.api.FoodRecipeApiClient
import ru.internetcloud.foody4.data.network.api.FoodRecipeApiService
import ru.internetcloud.foody4.data.repository.FoodFilterRepositoryImpl
import ru.internetcloud.foody4.data.repository.FoodRecipeRepositoryImpl
import ru.internetcloud.foody4.domain.repository.FoodFilterRepository
import ru.internetcloud.foody4.domain.repository.FoodRecipeRepository

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindFoodFilterRepository(impl: FoodFilterRepositoryImpl): FoodFilterRepository

    @ApplicationScope
    @Binds
    fun bindFoodRecipeRepository(impl: FoodRecipeRepositoryImpl): FoodRecipeRepository

    @ApplicationScope
    @Binds
    fun bindFoodRecipeRemoteDataSource(impl: FoodRecipeRemoteDataSourceImpl): FoodRecipeRemoteDataSource

    @ApplicationScope
    @Binds
    fun bindFoodRecipeLocalDataSource(impl: FoodRecipeLocalDataSourceImpl): FoodRecipeLocalDataSource

    companion object {

        @ApplicationScope
        @Provides
        fun provideAppDao(
            application: Application
        ): AppDao {
            return AppDatabase.getInstance(application).appDao()
        }

        @ApplicationScope
        @Provides
        fun provideApiClient(): FoodRecipeApiService {
            return FoodRecipeApiClient().client
        }
    }
}
