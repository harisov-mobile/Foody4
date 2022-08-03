package ru.internetcloud.foody4.presentation.flow

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import ru.internetcloud.foody4.R
import ru.internetcloud.foody4.domain.model.FoodRecipe
import ru.internetcloud.foody4.presentation.Screens
import ru.internetcloud.foody4.presentation.favorites.FavoriteRecipeListFragment
import ru.internetcloud.foody4.presentation.joke.FoodJokeFragment
import ru.internetcloud.foody4.presentation.recipe_list.FoodRecipeListFragment
import ru.internetcloud.foody4.presentation.util.FragmentNavigator

class MainFlowFragment : Fragment(), FoodRecipeListFragment.Callbacks {

    interface Callbacks {
        fun onFoodRecipeItemClick(foodRecipe: FoodRecipe)
    }

//    private val cicerone: Cicerone<Router> = Cicerone.create()
//    private val router = cicerone.router
//    private lateinit var navigator: Navigator

    private lateinit var toolbar: Toolbar
    private lateinit var bottomNavigationBar: BottomNavigationBar

//    private val currentFragment: Fragment?
//        get() = childFragmentManager.findFragmentById(R.id.container)

    private var hostActivity: Callbacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.i("rustam", " onAttach - $this")

        hostActivity = context as Callbacks
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        navigator = FragmentNavigator(
//            containerId = R.id.main_flow_fragment_container,
//            fragmentManager = childFragmentManager,
//            parentFragmentManager = parentFragmentManager
//        )

        Log.i("rustam", " onCreate - $this")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("rustam", " onCreateView - $this")
        return inflater.inflate(R.layout.fragment_main_flow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("rustam", " onViewCreated - 1 - $this")

        toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            Toast.makeText(context, "press UP", Toast.LENGTH_SHORT).show()
        }

        bottomNavigationBar = view.findViewById(R.id.bottom_navigation_bar)

        initViews()

        if (savedInstanceState == null) {
            bottomNavigationBar.selectTab(0, true)
        }

        Log.i("rustam", " onViewCreated - 2 - $this")
    }

    private fun initViews() {
        bottomNavigationBar
            .addItem(BottomNavigationItem(R.drawable.ic_menu_book, R.string.recipes))
            .addItem(BottomNavigationItem(R.drawable.ic_star, R.string.favorite_recipes))
            .addItem(BottomNavigationItem(R.drawable.ic_emoji_emotions, R.string.joke))
            .initialise()

        bottomNavigationBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {

            override fun onTabSelected(position: Int) {
                when (position) {
                    0 -> {
                        toolbar.title = getString(R.string.recipes)
                        // router.newRootScreen(Screens.getFoodRecipeListScreen())
                        showFragment(FoodRecipeListFragment())
                    }
                    1 -> {
                        toolbar.title = getString(R.string.favorite_recipes)
                        // router.newRootScreen(Screens.getFavoriteRecipeListScreen())
                        showFragment(FavoriteRecipeListFragment())
                    }
                    2 -> {
                        toolbar.title = getString(R.string.joke)
                        // router.newRootScreen(Screens.getFoodJokeScreen())
                        showFragment(FoodJokeFragment())
                    }
                }
                bottomNavigationBar.selectTab(position, false)
            }

            override fun onTabUnselected(position: Int) {}

            override fun onTabReselected(position: Int) {
                onTabSelected(position)
            }
        })
    }

//    override fun onResume() {
//        super.onResume()
//        cicerone.getNavigatorHolder().setNavigator(navigator)
//    }
//
//    override fun onPause() {
//        cicerone.getNavigatorHolder().removeNavigator()
//        super.onPause()
//    }

    override fun onDetach() {
        super.onDetach()

        hostActivity = null
    }

    override fun onFoodRecipeItemClick(foodRecipe: FoodRecipe) {
        // или фрагмент сам будет обрабатывать или отправит наверх:
        hostActivity?.onFoodRecipeItemClick(foodRecipe) // отправим на уровень вверх
    }

    private fun showFragmentWithBackStack(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.main_flow_fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun showFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.main_flow_fragment_container, fragment)
            .commit()
    }

}
