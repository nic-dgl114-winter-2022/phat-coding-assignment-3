package com.example.phat_coding_assignment_3.view_models

import androidx.lifecycle.*
import com.example.phat_coding_assignment_3.R
import com.example.phat_coding_assignment_3.data.fruit.Fruit
import com.example.phat_coding_assignment_3.data.land.Land
import com.example.phat_coding_assignment_3.data.land.LandDao
import kotlinx.coroutines.launch

class LandViewModel(private val landDao: LandDao) : ViewModel() {

    /*
    *   Initialize data for Land
    * */
    fun initializeLands() {
        addNewLand("Land #1", "finished", "2")
        addNewLand("Land #2", "pending", "3")
        addNewLand("Land #3", "finished", "4")
        addNewLand("Land #4", "pending", "5")
        addNewLand("Land #5", "pending", "6")
        addNewLand("Land #6", "finished", "7")
        addNewLand("Land #7", "pending", "8")
        addNewLand("Land #8", "finished", "9")
        addNewLand("Land #9", "finished", "10")
    }

    // Cache all fruits
    val allLands: LiveData<List<Land>> = landDao.getLands().asLiveData()

    /*
    * Add new Fruit to database
    * */
    private fun addNewLand(
        landName: String,
        landStatus: String,
        fruitId: String
    ) {
        val newLand = getNewLandEntry(landName, landStatus, fruitId)
        insertLand(newLand)
    }

    private fun insertLand(land: Land) {
        viewModelScope.launch {
            landDao.insert(land)
        }
    }

    /*
    *  Create Fruit Entry
    * */
    private fun getNewLandEntry(
        landName: String,
        landStatus: String,
        fruitId: String
    ): Land {
        return Land(
            landName = landName,
            landStatus = landStatus,
            fruitId = fruitId.toInt()
        )
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class LandViewModelFactory(private val landDao: LandDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LandViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LandViewModel(landDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

