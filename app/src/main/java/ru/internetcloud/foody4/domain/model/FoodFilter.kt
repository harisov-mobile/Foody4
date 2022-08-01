package ru.internetcloud.foody4.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodFilter(
    var mealTypeTag: String = "",
    var dietTypeTag: String = ""
) : Parcelable
