package com.example.phat_coding_assignment_3.view_models

import android.util.Log
import androidx.lifecycle.*
import com.example.phat_coding_assignment_3.data.fruit.Fruit
import com.example.phat_coding_assignment_3.data.fruit.FruitDao
import kotlinx.coroutines.launch

class InventoryViewModel(private val fruitDao: FruitDao) : ViewModel() {

    val allFruits: LiveData<List<Fruit>> = fruitDao.getFruits().asLiveData()

    /*
    * Add new Fruit to database
    * */

    fun addNewFruit(
        fruitName: String,
        fruitImageResourceId: String,
        fruitPrice: String,
        fruitCount: String
    ) {
        val newFruit = getNewFruitEntry(fruitName, fruitImageResourceId, fruitPrice, fruitCount)
        insertFruit(newFruit)
    }

    private fun insertFruit(fruit: Fruit) {
        Log.d("Fruit", fruit.toString())
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

