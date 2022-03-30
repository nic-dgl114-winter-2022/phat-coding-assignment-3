package com.example.phat_coding_assignment_3.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.phat_coding_assignment_3.R
import com.example.phat_coding_assignment_3.data.MainApplication
import com.example.phat_coding_assignment_3.data.fruit.Fruit
import com.example.phat_coding_assignment_3.databinding.FragmentHomeBinding
import com.example.phat_coding_assignment_3.view_models.InventoryViewModel
import com.example.phat_coding_assignment_3.view_models.InventoryViewModelFactory


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as MainApplication).database.fruitDao()
        )
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        // Create all fruits
//        viewModel.initializeFruits()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Access to Inventory
        binding.inventoryImage.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_inventoryFragment)
        }

        // Access to Market
        binding.marketImage.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_marketFragment)
        }

        viewModel.allFruits.observe(this.viewLifecycleOwner) { fruits ->
            for (fruit in fruits) {
                loadFruitQuantity(fruit)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadFruitQuantity(fruit: Fruit) {
        when(fruit.fruitName) {
            "Strawberry" -> binding.strawberryQuantity.text = fruit.fruitQuantityInStock.toString()
            "Apple" -> binding.appleQuantity.text = fruit.fruitQuantityInStock.toString()
            "Lemon" -> binding.lemonQuantity.text = fruit.fruitQuantityInStock.toString()
            "Orange" -> binding.orangeQuantity.text = fruit.fruitQuantityInStock.toString()
            "Mango" -> binding.mangoQuantity.text = fruit.fruitQuantityInStock.toString()
            "Coconut" -> binding.coconutQuantity.text = fruit.fruitQuantityInStock.toString()
            "Cherry" -> binding.cherryQuantity.text = fruit.fruitQuantityInStock.toString()
            "Green Grape" -> binding.greenGrapeQuantity.text = fruit.fruitQuantityInStock.toString()
            "Purple Grape" -> binding.purpleGrapeQuantity.text = fruit.fruitQuantityInStock.toString()
        }
    }
}