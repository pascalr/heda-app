package com.heda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.heda.databinding.RecipesFragmentBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RecipesFragment : Fragment() {

    private var _binding: RecipesFragmentBinding? = null
    private lateinit var recipeAdapter: RecipeAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = RecipesFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeAdapter = RecipeAdapter(mutableListOf(Recipe("Bread"), Recipe("Pizza")))

        binding.rvRecipeItems.adapter = recipeAdapter
        binding.rvRecipeItems.layoutManager = LinearLayoutManager(context)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_RecipesFragment_to_HomeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}