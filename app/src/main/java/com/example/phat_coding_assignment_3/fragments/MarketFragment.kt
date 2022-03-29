package com.example.phat_coding_assignment_3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phat_coding_assignment_3.R
import com.example.phat_coding_assignment_3.adapters.InventoryAdapter
import com.example.phat_coding_assignment_3.adapters.MarketAdapter
import com.example.phat_coding_assignment_3.databinding.FragmentInventoryBinding
import com.example.phat_coding_assignment_3.databinding.FragmentMarketBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MarketFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MarketFragment : Fragment() {

    // Set up view binding
    private var _binding: FragmentMarketBinding? = null
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
        _binding = FragmentMarketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up recycler view
        recyclerView = binding.marketRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MarketAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}