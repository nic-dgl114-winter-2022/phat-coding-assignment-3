package com.example.phat_coding_assignment_3.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phat_coding_assignment_3.R
import com.example.phat_coding_assignment_3.adapters.InventoryAdapter
import com.example.phat_coding_assignment_3.data.MainApplication
import com.example.phat_coding_assignment_3.databinding.FragmentHomeBinding
import com.example.phat_coding_assignment_3.databinding.FragmentInventoryBinding
import com.example.phat_coding_assignment_3.databinding.InventoryItemBinding
import com.example.phat_coding_assignment_3.view_models.InventoryViewModel
import com.example.phat_coding_assignment_3.view_models.InventoryViewModelFactory


/**
 * A simple [Fragment] subclass.
 * Use the [InventoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InventoryFragment : Fragment() {
    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as MainApplication).database.fruitDao()
        )
    }

    // Binding
    private var _binding: FragmentInventoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInventoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up recycler view
        binding.inventoryRecyclerView.layoutManager = GridLayoutManager(context, 4)
        val adapter = InventoryAdapter()
        binding.inventoryRecyclerView.adapter = adapter

        // Attach an observer on the allItems list to update the UI automatically when the data
        // changes.
//        viewModel.allFruits.observe(this.viewLifecycleOwner) { fruits ->
//            fruits.let {
//
//            }
//        }

        binding.addFruitButton.setOnClickListener {
            addNewFruit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addNewFruit() {
        viewModel.addNewFruit("Apple", R.drawable.apple.toString(), "3", "10")
//        Log.d("Fruit", "Apple")
//        Log.d("Fruit", R.drawable.apple.toString())
//        Log.d("Fruit", "3")
//        Log.d("Fruit", "10")
    }
}