package ru.internetcloud.foody4.presentation.flow

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import ru.internetcloud.foody4.R
import ru.internetcloud.foody4.domain.model.FoodRecipe
import ru.internetcloud.foody4.presentation.util.FragmentNavigator

class TabFlowFragment : Fragment() {

    private val cicerone: Cicerone<Router> = Cicerone.create()
    private val router = cicerone.router
    private lateinit var navigator: Navigator

    private lateinit var toolbar: Toolbar

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

        navigator = FragmentNavigator(
            containerId = R.id.tab_flow_fragment_container,
            fragmentManager = childFragmentManager,
            parentFragmentManager = parentFragmentManager
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_tab_flow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        readArgs()

        toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            Toast.makeText(context, "Tab Flow", Toast.LENGTH_SHORT).show()
        }

        if (savedInstanceState == null) {
            // bottomNavigationBar.selectTab(0, true)
        }
    }

    private fun readArgs() {

        arguments?.let { args ->
            val foodRecipe = args.getParcelable<FoodRecipe>(FOOD_RECIPE_KEY)
        }
    }
}
