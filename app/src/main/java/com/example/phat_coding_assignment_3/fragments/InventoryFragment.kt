package com.example.phat_coding_assignment_3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phat_coding_assignment_3.R
import com.example.phat_coding_assignment_3.adapters.InventoryAdapter
import com.example.phat_coding_assignment_3.databinding.FragmentHomeBinding
import com.example.phat_coding_assignment_3.databinding.FragmentInventoryBinding
import com.example.phat_coding_assignment_3.databinding.InventoryItemBinding


/**
 * A simple [Fragment] subclass.
 * Use the [InventoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InventoryFragment : Fragment() {

    // Binding
    private var _binding: FragmentInventoryBinding? = null
    private val binding get() = _binding!!

    // Recycler view
    private lateinit var recyclerView: RecyclerView

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
        recyclerView = binding.inventoryRecyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 4)
        recyclerView.adapter = InventoryAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}