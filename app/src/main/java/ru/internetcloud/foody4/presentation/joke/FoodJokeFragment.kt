package ru.internetcloud.foody4.presentation.joke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.internetcloud.foody4.databinding.FragmentFoodJokeBinding

class FoodJokeFragment : Fragment() {

    private var _binding: FragmentFoodJokeBinding? = null
    val binding: FragmentFoodJokeBinding
        get() = _binding ?: throw IllegalStateException("FragmentFoodJokeBinding is null")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFoodJokeBinding.inflate(inflater, container, false)
        return binding.root
    }
}
