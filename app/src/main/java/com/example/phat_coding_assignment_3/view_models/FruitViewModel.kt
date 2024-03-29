package com.example.phat_coding_assignment_3.view_models

import android.util.Log
import androidx.lifecycle.*
import com.example.phat_coding_assignment_3.R
import com.example.phat_coding_assignment_3.data.fruit.Fruit
import com.example.phat_coding_assignment_3.data.fruit.FruitDao
import kotlinx.coroutines.launch

class FruitViewModel(private val fruitDao: FruitDao) : ViewModel() {

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

    private fun hasValidAmount(fruit: Fruit) {

    }

    fun sellFruit(fruit: Fruit) {
        if (fruit.fruitQuantityInStock > 0) {
            editFruit(fruit)
        }
    }

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

    /*
    *   Get Fruit
    * */
    fun getFruitById(id: Int): LiveData<Fruit> {
        return fruitDao.getFruit(id).asLiveData()
    }

    /*
    *   Increase stock
    * */
    fun increaseStock(fruitId: Int, amount: Int) {
        viewModelScope.launch {
            fruitDao.increaseAmount(id = fruitId, amount = amount)
        }
    }

    /*
    *  Update fruit to database
    * */
    fun updateFruit(
        fruitId: Int,
        fruitName: String,
        fruitImageResourceId: String,
        fruitPrice: String,
        fruitCount: String
    ) {
        val updatedFruit =
            getUpdatedFruitEntry(fruitId, fruitName, fruitImageResourceId, fruitPrice, fruitCount)
        editFruit(updatedFruit)
    }

    private fun editFruit(fruit: Fruit) {
        viewModelScope.launch {
            fruitDao.update(fruit)
        }
    }

    /*
    *  Update Fruit Entry
    * */
    private fun getUpdatedFruitEntry(
        fruitId: Int,
        fruitName: String,
        fruitImageResourceId: String,
        fruitPrice: String,
        fruitCount: String
    ): Fruit {
        return Fruit(
            id = fruitId,
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
class FruitViewModelFactory(private val fruitDao: FruitDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FruitViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FruitViewModel(fruitDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

