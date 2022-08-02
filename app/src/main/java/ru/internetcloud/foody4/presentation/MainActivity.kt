package ru.internetcloud.foody4.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import ru.internetcloud.foody4.R
import ru.internetcloud.foody4.domain.model.FoodRecipe
import ru.internetcloud.foody4.presentation.recipe_list.FoodRecipeListFragment

class MainActivity : AppCompatActivity(), FoodRecipeListFragment.Callbacks {

    private val cicerone: Cicerone<Router> = Cicerone.create()
    private val router = cicerone.router
    private val navigator: Navigator = AppNavigator(activity = this, R.id.container)

//    private val currentFragment: Fragment?
//        get() = supportFragmentManager.findFragmentById(R.id.container) as? Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            router.newRootScreen(Screens.getMainFlowScreen())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        cicerone.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        cicerone.getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    override fun onFoodRecipeItemClick(foodRecipe: FoodRecipe) {
        router.navigateTo(Screens.getTabFlowScreen(foodRecipe))
    }

}
