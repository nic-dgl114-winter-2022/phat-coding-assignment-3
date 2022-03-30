package com.example.phat_coding_assignment_3.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phat_coding_assignment_3.adapters.MarketAdapter
import com.example.phat_coding_assignment_3.data.MainApplication
import com.example.phat_coding_assignment_3.data.fruit.Fruit
import com.example.phat_coding_assignment_3.data.user.User
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

        var adapter = MarketAdapter { fruit: Fruit, sellingAmount: String ->
            // Pass sell function to adapter
            sellFruit(fruit, sellingAmount)
        }
        recyclerView.adapter = adapter

        // Observe all users
        userViewModel.allUsers.observe(this.viewLifecycleOwner) { users ->
            if (users.isNotEmpty()) {
                // Get the first user because only 1 player is playing this game
                player = users[0]
            }
        }

        // Observe all fruits
        fruitViewModel.allFruits.observe(this.viewLifecycleOwner) { fruits ->
            if (fruits.isNotEmpty()) {
                fruits.let {
                    adapter.submitList(it)

                    // Calculate total value and display it
                    calculateTotalValue(it)

                    // Copy the list to handle the selling
                    fruitList = it
                }

                // Listener for "Sell all" button
                if (totalValue > 0) {
                    binding.sellAllButton.setOnClickListener {
                        sellAllFruits()
                    }
                    binding.sellAllButton.isEnabled = true
                } else {
                    binding.sellAllButton.isEnabled = false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun sellFruit(fruit: Fruit, sellingAmount: String) {
        val _sellingAmount: Int = if (sellingAmount == "" || sellingAmount == "0") {
            0
        } else {
            sellingAmount.toInt()
        }
        val totalMoney = fruit.fruitPrice * _sellingAmount
        val remainingAmount = fruit.fruitQuantityInStock - _sellingAmount

        // Update stock
        fruitViewModel.updateFruit(
            fruitId = fruit.id,
            fruitName = fruit.fruitName,
            fruitImageResourceId = fruit.fruitImageResourceId.toString(),
            fruitPrice = fruit.fruitPrice.toString(),
            fruitCount = remainingAmount.toString()
        )

        // Update money
        val money = player.money + totalMoney
        userViewModel.updateUser(player.id, money)

    }

    private fun calculateTotalValue(fruits: List<Fruit>) {
        var _totalValue = 0
        fruits.forEach {
            _totalValue += it.fruitPrice * it.fruitQuantityInStock
        }

        // Display total value
        totalValue = _totalValue
        binding.totalSellAmount.text = _totalValue.toString()
    }


    // Sell all the available fruits
    private fun sellAllFruits() {
        // Update stock
        fruitList.forEach {
            fruitViewModel.updateFruit(
                fruitId = it.id,
                fruitName = it.fruitName,
                fruitImageResourceId = it.fruitImageResourceId.toString(),
                fruitPrice = it.fruitPrice.toString(),
                fruitCount = "0"
            )
        }

        // Update money
        val money = player.money + totalValue
        userViewModel.updateUser(player.id, money)
    }
}