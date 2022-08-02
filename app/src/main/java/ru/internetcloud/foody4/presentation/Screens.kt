package ru.internetcloud.foody4.presentation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.internetcloud.foody4.domain.model.FoodRecipe
import ru.internetcloud.foody4.presentation.favorites.FavoriteRecipeListFragment
import ru.internetcloud.foody4.presentation.joke.FoodJokeFragment
import ru.internetcloud.foody4.presentation.recipe_list.FoodRecipeListFragment
import ru.internetcloud.foody4.presentation.flow.MainFlowFragment
import ru.internetcloud.foody4.presentation.flow.TabFlowFragment

object Screens {

    fun getMainFlowScreen() = FragmentScreen {
            MainFlowFragment()
    }

    fun getFoodRecipeListScreen() = FragmentScreen {
            FoodRecipeListFragment()
    }

    fun getFoodJokeScreen() = FragmentScreen {
            FoodJokeFragment()
    }

    fun getFavoriteRecipeListScreen() = FragmentScreen {
            FavoriteRecipeListFragment()
    }

    fun getTabFlowScreen(foodRecipe: FoodRecipe) = FragmentScreen {
            TabFlowFragment.newInstance(foodRecipe)
    }



}
