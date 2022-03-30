package com.example.phat_coding_assignment_3.view_models

import androidx.lifecycle.*
import com.example.phat_coding_assignment_3.data.land.Land
import com.example.phat_coding_assignment_3.data.land.LandDao
import kotlinx.coroutines.launch

class LandViewModel(private val landDao: LandDao) : ViewModel() {
    /*
    *   Initialize data for Land
    * */
    fun initializeLands() {
        addNewLand("Land #1", "finished", "1", "6")
        addNewLand("Land #2", "pending", "2", "11")
        addNewLand("Land #3", "finished", "3", "8")
        addNewLand("Land #4", "pending", "4", "20")
        addNewLand("Land #5", "pending", "5", "15")
        addNewLand("Land #6", "finished", "6", "30")
        addNewLand("Land #7", "pending", "7", "19")
        addNewLand("Land #8", "finished", "8", "21")
        addNewLand("Land #9", "finished", "9", "10")
    }

    // Cache all lands
    val allLands: LiveData<List<Land>> = landDao.getLands().asLiveData()

    /*
    * Add new Land to database
    * */
    private fun addNewLand(
        landName: String,
        landStatus: String,
        fruitId: String,
        harvestAmount: String
    ) {
        val newLand = getNewLandEntry(landName, landStatus, fruitId, harvestAmount)
        insertLand(newLand)
    }

    private fun insertLand(land: Land) {
        viewModelScope.launch {
            landDao.insert(land)
        }
    }

    /*
    *  Create Land Entry
    * */
    private fun getNewLandEntry(
        landName: String,
        landStatus: String,
        fruitId: String,
        harvestAmount: String
    ): Land {
        return Land(
            landName = landName,
            landStatus = landStatus,
            fruitId = fruitId.toInt(),
            harvestAmount = harvestAmount.toInt()
        )
    }

    fun grow(amount: Int) {
        viewModelScope.launch {
            landDao.grow(amount)
        }
    }

    fun harvestFruit(
        landId: Int,
        landName: String,
        landStatus: String,
        fruitId: String,
        harvestAmount: String
    ) {
        val harvestedLand =
            getUpdatedLandEntry(landId, landName, landStatus, fruitId, harvestAmount)

        if (harvestedLand.landStatus == "finished" && harvestedLand.harvestAmount > 0) {
            updateLand(landId, landName, "pending", fruitId, "0")
        }
    }

    fun updateLand(
        landId: Int,
        landName: String,
        landStatus: String,
        fruitId: String,
        harvestAmount: String
    ) {
        val updatedLand = getUpdatedLandEntry(landId, landName, landStatus, fruitId, harvestAmount)
        editLand(updatedLand)
    }

    private fun editLand(land: Land) {
        viewModelScope.launch {
            landDao.update(land)
        }
    }

    /*
    *   Get Land Entry with id
    * */
    private fun getUpdatedLandEntry(
        landId: Int,
        landName: String,
        landStatus: String,
        fruitId: String,
        harvestAmount: String
    ): Land {
        return Land(
            id = landId,
            landName = landName,
            landStatus = landStatus,
            fruitId = fruitId.toInt(),
            harvestAmount = harvestAmount.toInt()
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

