package ru.internetcloud.foody4.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.internetcloud.foody4.databinding.FragmentFavoriteRecipeListBinding

class FavoriteRecipeListFragment : Fragment() {

    private var _binding: FragmentFavoriteRecipeListBinding? = null
    val binding: FragmentFavoriteRecipeListBinding
        get() = _binding ?: throw IllegalStateException("FragmentFavoriteRecipeListBinding is null")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFavoriteRecipeListBinding.inflate(inflater, container, false)
        return binding.root
    }
}
