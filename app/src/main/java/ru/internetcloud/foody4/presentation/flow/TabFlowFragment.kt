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
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import com.google.android.material.tabs.TabLayoutMediator
import ru.internetcloud.foody4.R
import ru.internetcloud.foody4.databinding.FragmentTabFlowBinding
import ru.internetcloud.foody4.domain.model.FoodRecipe
import ru.internetcloud.foody4.presentation.recipe_detail.ViewPagerAdapter
import ru.internetcloud.foody4.presentation.recipe_detail.ingredients.IngredientsFragment
import ru.internetcloud.foody4.presentation.recipe_detail.instructions.InstructionsFragment
import ru.internetcloud.foody4.presentation.recipe_detail.overview.OverviewFragment
import ru.internetcloud.foody4.presentation.util.FragmentNavigator
import java.lang.IllegalStateException

class TabFlowFragment : Fragment() {

//    private val cicerone: Cicerone<Router> = Cicerone.create()
//    private val router = cicerone.router
//    private lateinit var navigator: Navigator

    private lateinit var toolbar: Toolbar

    private lateinit var foodRecipe: FoodRecipe

    private var _binding: FragmentTabFlowBinding? = null
    val binding: FragmentTabFlowBinding
        get() = _binding ?: throw IllegalStateException("FragmentTabFlowBinding is null")

    companion object {

        private const val FOOD_RECIPE_KEY = "food_recipe_key"

        fun newInstance(foodRecipe: FoodRecipe): TabFlowFragment {
            return TabFlowFragment().apply {
                val args = Bundle()
                args.putParcelable(FOOD_RECIPE_KEY, foodRecipe)
                arguments = args
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.i("rustam", " onAttach - $this")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        navigator = FragmentNavigator(
//            containerId = R.id.view_pager,
//            fragmentManager = childFragmentManager,
//            parentFragmentManager = parentFragmentManager
//        )

        Log.i("rustam", " onCreate - $this")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTabFlowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("rustam", " onViewCreated - 1 - $this")

        readArgs()

        toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            Toast.makeText(context, "Tab Flow", Toast.LENGTH_SHORT).show()
        }

        updateUI(savedInstanceState)

        Log.i("rustam", " onViewCreated - 2 - $this")
    }

    private fun readArgs() {

        arguments?.let { args ->
            foodRecipe = args.getParcelable<FoodRecipe>(FOOD_RECIPE_KEY)
                ?: throw IllegalStateException("FoodRecipe can not be null")
        }
    }

    private fun getTabFragments(savedInstanceState: Bundle?): List<Fragment>  {

        val fragments = mutableListOf<Fragment>()

        if (savedInstanceState == null) {
            fragments.add(OverviewFragment.newInstance(foodRecipe))
            fragments.add(IngredientsFragment.newInstance(foodRecipe))
            fragments.add(InstructionsFragment.newInstance(foodRecipe))

        } else {
            val existingFragments = parentFragmentManager.fragments.toList()

            val overviewFragment = existingFragments.find { it is OverviewFragment } ?: OverviewFragment.newInstance(foodRecipe)
            fragments.add(overviewFragment)

            val ingredientsFragment = existingFragments.find { it is IngredientsFragment } ?: IngredientsFragment.newInstance(foodRecipe)
            fragments.add(ingredientsFragment)

            val instructionsFragment = existingFragments.find { it is InstructionsFragment } ?: InstructionsFragment.newInstance(foodRecipe)
            fragments.add(instructionsFragment)

            for (currentFragment in existingFragments) {
                Log.i("rustam", " currentFragment - $currentFragment")
            }
        }

        return fragments.toList()
    }

    private fun updateUI(savedInstanceState: Bundle?) {

        val fragments = getTabFragments(savedInstanceState)

        Log.i("rustam", " fragments - ${fragments.size}")

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")
        titles.add("Instructions")

        Log.i("rustam", " titles - ${titles.size}")

        val pagerAdapter = ViewPagerAdapter(fragments, parentFragmentManager, lifecycle)

        Log.i("rustam", " pagerAdapter - ${pagerAdapter}")

        showFragment(fragments[0])

//        binding.viewPager.isUserInputEnabled = false
//        binding.viewPager.adapter = pagerAdapter
//
//        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
//            tab.text = titles[position]
//        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun showFragmentWithBackStack(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.temp_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun showFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.temp_container, fragment)
            .commit()
    }

}
