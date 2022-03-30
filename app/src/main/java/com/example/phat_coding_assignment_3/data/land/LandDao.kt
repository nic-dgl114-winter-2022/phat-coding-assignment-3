package com.example.phat_coding_assignment_3.data.land

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LandDao {
    @Query("SELECT * FROM land ORDER BY name ASC")
    fun getLands(): Flow<List<Land>>

    @Query("SELECT * FROM land WHERE id = :id")
    fun getLand(id: Int): Flow<Land>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(land: Land)

    @Update
    suspend fun update(land: Land)

}