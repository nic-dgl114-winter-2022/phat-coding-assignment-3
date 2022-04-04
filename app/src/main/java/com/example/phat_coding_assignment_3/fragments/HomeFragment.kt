package com.example.phat_coding_assignment_3.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.phat_coding_assignment_3.R
import com.example.phat_coding_assignment_3.data.MainApplication
import com.example.phat_coding_assignment_3.data.fruit.Fruit
import com.example.phat_coding_assignment_3.data.land.Land
import com.example.phat_coding_assignment_3.data.user.User
import com.example.phat_coding_assignment_3.databinding.FragmentHomeBinding
import com.example.phat_coding_assignment_3.view_models.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // Fruit
    lateinit var fruitList: List<Fruit>
    lateinit var userList: List<User>
    lateinit var landList: List<Land>

    private var isGrowing = false

    // View model
    private val landViewModel: LandViewModel by activityViewModels {
        LandViewModelFactory(
            (activity?.application as MainApplication).database.landDao()
        )
    }
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

    // Binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

        // Observe all users
        userViewModel.allUsers.observe(this.viewLifecycleOwner) { users ->
            if (users.isNotEmpty()) {
                userList = users
                // Get the first user because only 1 player is playing this game
                val player = users[0]
                binding.moneyAmount.text = player.money.toString()
            } else {
                // Initialize the data
                userViewModel.initializeUsers()
            }
        }

        // Observe lands
        landViewModel.allLands.observe(this.viewLifecycleOwner) { lands ->
            if (lands.isNotEmpty()) {
                landList = lands
                for (land in lands) {
                    loadLand(land)
                }

                // Automatically increase harvest amount by 6 in 5 seconds ;)
                if (!isGrowing) {
                    startGrowing(5000, 6)
                }
            } else {
                // Initialize the data
                landViewModel.initializeLands()
            }
        }

        // Observe all fruits
        fruitViewModel.allFruits.observe(this.viewLifecycleOwner)
        { fruits ->
            if (fruits.isNotEmpty()) {
                fruitList = fruits

            } else {
                // Initialize the data
                fruitViewModel.initializeFruits()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    I can see how you are sort of forced into the pattern below, but it's definitely awkward and
//    fairly repetitive. At the very least you should break out all the common code from each of the
//    case blocks to reduce overall repetitiveness.

    // Load UI based on data
    private fun loadLand(land: Land) {
        findFruitById(land.id)
        findFruitById(land.fruitId)
        when (land.id) {
            1 -> { // Strawberry
                // I think it's weird that this doesn't work? But I could be misunderstanding your
                // approach here. Either way, it would be good to refer to the data in the entity
                // rather than to the image resource directly
                binding.land1FruitImage.setImageResource(fruitList[land.fruitId].fruitImageResourceId)
                binding.land1Quantity.text = land.harvestAmount.toString()

                val landType = if (land.landStatus == "pending") {
                    R.drawable.soil_land
                } else {
                    R.drawable.green_land
                }

                // Set (soil | green) land
                binding.land1.setImageResource(landType)

                // Harvest Listener
                if (landType == R.drawable.green_land) {
                    harvestOnListener(land)
                }
            }
            2 -> { // Apple
                binding.land2FruitImage.setImageResource(R.drawable.apple)
                binding.land2Quantity.text = land.harvestAmount.toString()

                val landType = if (land.landStatus == "pending") {
                    R.drawable.soil_land
                } else {
                    R.drawable.green_land
                }

                // Set (soil | green) land
                binding.land2.setImageResource(landType)

                // Harvest Listener
                if (landType == R.drawable.green_land) {
                    harvestOnListener(land)
                }
            }
            3 -> { // Lemon
                binding.land3FruitImage.setImageResource(R.drawable.lemon)
                binding.land3Quantity.text = land.harvestAmount.toString()

                val landType = if (land.landStatus == "pending") {
                    R.drawable.soil_land
                } else {
                    R.drawable.green_land
                }

                // Set (soil | green) land
                binding.land3.setImageResource(landType)

                // Harvest Listener
                if (landType == R.drawable.green_land) {
                    harvestOnListener(land)
                }
            }
            4 -> { // Orange
                binding.land4FruitImage.setImageResource(R.drawable.orange)
                binding.land4Quantity.text = land.harvestAmount.toString()

                val landType = if (land.landStatus == "pending") {
                    R.drawable.soil_land
                } else {
                    R.drawable.green_land
                }

                // Set (soil | green) land
                binding.land4.setImageResource(landType)

                // Harvest Listener
                if (landType == R.drawable.green_land) {
                    harvestOnListener(land)
                }
            }
            5 -> { // Mango
                binding.land5FruitImage.setImageResource(R.drawable.mango)
                binding.land5Quantity.text = land.harvestAmount.toString()

                val landType = if (land.landStatus == "pending") {
                    R.drawable.soil_land
                } else {
                    R.drawable.green_land
                }

                // Set (soil | green) land
                binding.land5.setImageResource(landType)

                // Harvest Listener
                if (landType == R.drawable.green_land) {
                    harvestOnListener(land)
                }
            }
            6 -> { // Coconut
                binding.land6FruitImage.setImageResource(R.drawable.coconut)
                binding.land6Quantity.text = land.harvestAmount.toString()

                val landType = if (land.landStatus == "pending") {
                    R.drawable.soil_land
                } else {
                    R.drawable.green_land
                }

                // Set (soil | green) land
                binding.land6.setImageResource(landType)

                // Harvest Listener
                if (landType == R.drawable.green_land) {
                    harvestOnListener(land)
                }
            }
            7 -> { // Cherry
                binding.land7FruitImage.setImageResource(R.drawable.cherry)
                binding.land7Quantity.text = land.harvestAmount.toString()

                val landType = if (land.landStatus == "pending") {
                    R.drawable.soil_land
                } else {
                    R.drawable.green_land
                }

                // Set (soil | green) land
                binding.land7.setImageResource(landType)

                // Harvest Listener
                if (landType == R.drawable.green_land) {
                    harvestOnListener(land)
                }
            }
            8 -> { // Green Grape
                binding.land8FruitImage.setImageResource(R.drawable.green_grape)
                binding.land8Quantity.text = land.harvestAmount.toString()

                val landType = if (land.landStatus == "pending") {
                    R.drawable.soil_land
                } else {
                    R.drawable.green_land
                }

                // Set (soil | green) land
                binding.land8.setImageResource(landType)

                // Harvest Listener
                if (landType == R.drawable.green_land) {
                    harvestOnListener(land)
                }
            }
            else -> { // 9 - Purple Grape
                binding.land9FruitImage.setImageResource(R.drawable.purple_grape)
                binding.land9Quantity.text = land.harvestAmount.toString()

                val landType = if (land.landStatus == "pending") {
                    R.drawable.soil_land
                } else {
                    R.drawable.green_land
                }

                // Set (soil | green) land
                binding.land9.setImageResource(landType)

                // Harvest Listener
                if (landType == R.drawable.green_land) {
                    harvestOnListener(land)
                }
            }
        }
    }

    // Listener for harvesting
    private fun harvestOnListener(land: Land) {
        when (land.id) {
            1 -> {
                binding.land1.setOnClickListener {
                    harvest(land)
                    // Change to "soil" land
                    binding.land1.setImageResource(R.drawable.soil_land)
                }
            }
            2 -> {
                binding.land2.setOnClickListener {
                    harvest(land)
                    // Change to "soil" land
                    binding.land2.setImageResource(R.drawable.soil_land)
                }
            }
            3 -> {
                binding.land3.setOnClickListener {
                    harvest(land)
                    // Change to "soil" land
                    binding.land3.setImageResource(R.drawable.soil_land)
                }
            }
            4 -> {
                binding.land4.setOnClickListener {
                    harvest(land)
                    // Change to "soil" land
                    binding.land4.setImageResource(R.drawable.soil_land)
                }
            }
            5 -> {
                binding.land5.setOnClickListener {
                    harvest(land)
                    // Change to "soil" land
                    binding.land5.setImageResource(R.drawable.soil_land)
                }
            }
            6 -> {
                binding.land6.setOnClickListener {
                    harvest(land)
                    // Change to "soil" land
                    binding.land6.setImageResource(R.drawable.soil_land)
                }
            }
            7 -> {
                binding.land7.setOnClickListener {
                    harvest(land)
                    // Change to "soil" land
                    binding.land7.setImageResource(R.drawable.soil_land)
                }
            }
            8 -> {
                binding.land8.setOnClickListener {
                    harvest(land)
                    // Change to "soil" land
                    binding.land8.setImageResource(R.drawable.soil_land)
                }
            }
            else -> {
                binding.land9.setOnClickListener {
                    harvest(land)
                    // Change to "soil" land
                    binding.land9.setImageResource(R.drawable.soil_land)
                }
            }
        }
    }

    // Switch Status and change amount if it was harvested
    private fun harvest(land: Land) {
        // Harvest
        landViewModel.harvestFruit(
            land.id,
            land.landName,
            land.landStatus,
            land.fruitId.toString(),
            land.harvestAmount.toString()
        )

        val harvestedFruit = findFruitById(land.fruitId)
        // Update stock
        fruitViewModel.updateFruit(
            fruitId = harvestedFruit.id,
            fruitName = harvestedFruit.fruitName,
            fruitImageResourceId = harvestedFruit.fruitImageResourceId.toString(),
            fruitPrice = harvestedFruit.fruitPrice.toString(),
            fruitCount = (harvestedFruit.fruitQuantityInStock + land.harvestAmount).toString()
        )
    }

    private fun findFruitById(id: Int): Fruit {
        return fruitList.filter { it.id == id }[0]
    }

    // Increase amount after a fixed time
    private fun startGrowing(fixedTime: Long = 10000, increasingAmount: Int) {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                isGrowing = true
                landList.forEach {
                    val newHarvestAmount = it.harvestAmount + increasingAmount
                    val newStatus = if (it.landStatus == "pending") {
                        "finished"
                    } else {
                        it.landStatus
                    }
                    landViewModel.updateLand(
                        landId = it.id,
                        landName = it.landName,
                        landStatus = newStatus,
                        fruitId = it.fruitId.toString(),
                        harvestAmount = newHarvestAmount.toString()
                    )
                }
            }
        }, 0, fixedTime)
    }
}