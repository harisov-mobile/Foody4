package ru.internetcloud.foody4.data.network.dto

import com.google.gson.annotations.SerializedName

data class FoodJokeDTO(
    @SerializedName("text")
    val text: String
)
