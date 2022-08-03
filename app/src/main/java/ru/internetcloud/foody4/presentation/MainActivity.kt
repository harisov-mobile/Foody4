package ru.internetcloud.foody4.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.internetcloud.foody4.R
import ru.internetcloud.foody4.domain.model.FoodRecipe
import ru.internetcloud.foody4.presentation.flow.MainFlowFragment
import ru.internetcloud.foody4.presentation.flow.TabFlowFragment

class MainActivity :
    AppCompatActivity(),
    MainFlowFragment.Callbacks {

//    private val cicerone: Cicerone<Router> = Cicerone.create()
//    private val router = cicerone.router
//    private val navigator: Navigator = MyAppNavigator(activity = this, R.id.container)

//    private val currentFragment: Fragment?
//        get() = supportFragmentManager.findFragmentById(R.id.container) as? Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            // router.newRootScreen(Screens.getMainFlowScreen())
            showFragment(MainFlowFragment())
        }
    }

//    override fun onResumeFragments() {
//        super.onResumeFragments()
//        cicerone.getNavigatorHolder().setNavigator(navigator)
//    }
//
//    override fun onPause() {
//        cicerone.getNavigatorHolder().removeNavigator()
//        super.onPause()
//    }

    override fun onFoodRecipeItemClick(foodRecipe: FoodRecipe) {
        // router.navigateTo(Screens.getTabFlowScreen(foodRecipe))
        showFragmentWithBackStack(TabFlowFragment.newInstance(foodRecipe))
    }

    private fun showFragmentWithBackStack(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}
