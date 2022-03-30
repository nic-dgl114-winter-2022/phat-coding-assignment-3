package com.example.phat_coding_assignment_3.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phat_coding_assignment_3.R
import com.example.phat_coding_assignment_3.adapters.InventoryAdapter
import com.example.phat_coding_assignment_3.adapters.MarketAdapter
import com.example.phat_coding_assignment_3.data.MainApplication
import com.example.phat_coding_assignment_3.data.fruit.Fruit
import com.example.phat_coding_assignment_3.data.user.User
import com.example.phat_coding_assignment_3.databinding.FragmentInventoryBinding
import com.example.phat_coding_assignment_3.databinding.FragmentMarketBinding
import com.example.phat_coding_assignment_3.view_models.FruitViewModel
import com.example.phat_coding_assignment_3.view_models.FruitViewModelFactory
import com.example.phat_coding_assignment_3.view_models.UserViewModel
import com.example.phat_coding_assignment_3.view_models.UserViewModelFactory

/**
 * A simple [Fragment] subclass.
 * Use the [MarketFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MarketFragment : Fragment() {
    private val fruitViewModel: FruitViewModel by activityViewModels {
        FruitViewModelFactory(
            (activity?.application as MainApplication).database.fruitDao()
        )
    }

    private val userViewModel: UserViewModel by activityViewModels {
        UserViewModelFactory(
            (activity?.application as MainApplication).database.userDao()
        )
    }


    private lateinit var fruitList: List<Fruit>
    private var totalValue: Int = 0

    // User data
    private lateinit var player: User

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
        val adapter = MarketAdapter()
        recyclerView.adapter = adapter

        // Observe all users
        userViewModel.allUsers.observe(this.viewLifecycleOwner) { users ->
            // Get the first user because only 1 player is playing this game
            player = users[0]
        }

        // Observe all fruits
        fruitViewModel.allFruits.observe(this.viewLifecycleOwner) { fruits ->
            fruits.let {
                adapter.submitList(it)
            }

            // Calculate total value and display it
            calculateTotalValue(fruits)
        }

        // Listener for "Sell all" button
        binding.sellAllButton.setOnClickListener {
            sellAllFruits()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun calculateTotalValue (fruits: List<Fruit>) {
        fruits.forEach {
            totalValue += it.fruitPrice * it.fruitQuantityInStock
        }

        // Display total value
        binding.totalSellAmount.text = totalValue.toString()
    }


    // Sell all the available fruits
    private fun sellAllFruits() {

    }
}