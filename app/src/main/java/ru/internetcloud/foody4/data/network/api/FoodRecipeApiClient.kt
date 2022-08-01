package ru.internetcloud.foody4.data.network.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.internetcloud.foody4.BuildConfig

class FoodRecipeApiClient {

    private val okHttpClient: OkHttpClient =
        if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY })
                .build()
        } else {
            OkHttpClient.Builder()
                .build()
        }

    // необходимо эту переменную поместить ниже, чем okHttpClient
    // т.к. сначала должна пройти инициализация переменной okHttpClient
    val client: FoodRecipeApiService = getRetrofit().create(FoodRecipeApiService::class.java)

    private fun getRetrofit(): Retrofit {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SPOONACULAR_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }
}
