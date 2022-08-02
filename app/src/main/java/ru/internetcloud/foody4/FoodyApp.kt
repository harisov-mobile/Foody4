package ru.internetcloud.foody4

import android.app.Application
import ru.internetcloud.foody4.di.DaggerApplicationComponent

class FoodyApp : Application() {

    // помни про манифест: android:name=".FoodyApp"

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}
