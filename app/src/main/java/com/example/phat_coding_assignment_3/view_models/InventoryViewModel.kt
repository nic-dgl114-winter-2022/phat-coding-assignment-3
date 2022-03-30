package com.example.phat_coding_assignment_3.view_models

import android.content.ClipData
import android.util.Log
import androidx.lifecycle.*
import com.example.phat_coding_assignment_3.R
import com.example.phat_coding_assignment_3.data.fruit.Fruit
import com.example.phat_coding_assignment_3.data.fruit.FruitDao
import kotlinx.coroutines.launch

class InventoryViewModel(private val fruitDao: FruitDao) : ViewModel() {

    /*
    *   Initialize data for Fruit
    * */
    fun initializeFruits() {
        addNewFruit("Strawberry", R.drawable.strawberry.toString(), "1", "21")
        addNewFruit("Apple", R.drawable.apple.toString(), "5", "22")
        addNewFruit("Lemon", R.drawable.lemon.toString(), "2", "15")
        addNewFruit("Orange", R.drawable.orange.toString(), "4", "8")
        addNewFruit("Mango", R.drawable.mango.toString(), "6", "2")
        addNewFruit("Coconut", R.drawable.coconut.toString(), "7", "7")
        addNewFruit("Cherry", R.drawable.cherry.toString(), "1", "3")
        addNewFruit("Green Grape", R.drawable.green_grape.toString(), "12", "16")
        addNewFruit("Purple Grape", R.drawable.purple_grape.toString(), "2", "6")
    }

    // Cache all fruits
    val allFruits: LiveData<List<Fruit>> = fruitDao.getFruits().asLiveData()

    /*
    * Add new Fruit to database
    * */
    private fun addNewFruit(
        fruitName: String,
        fruitImageResourceId: String,
        fruitPrice: String,
        fruitCount: String
    ) {
        val newFruit = getNewFruitEntry(fruitName, fruitImageResourceId, fruitPrice, fruitCount)
        insertFruit(newFruit)
    }

    private fun insertFruit(fruit: Fruit) {
        viewModelScope.launch {
            fruitDao.insert(fruit)
        }
    }

    /*
    *  Create Fruit Entry
    * */
    private fun getNewFruitEntry(
        fruitName: String,
        fruitImageResourceId: String,
        fruitPrice: String,
        fruitCount: String
    ): Fruit {
        return Fruit(
            fruitName = fruitName,
            fruitImageResourceId = fruitImageResourceId.toInt(),
            fruitPrice = fruitPrice.toInt(),
            fruitQuantityInStock = fruitCount.toInt()
        )
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class InventoryViewModelFactory(private val fruitDao: FruitDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(fruitDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

