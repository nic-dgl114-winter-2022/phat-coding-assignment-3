package com.example.phat_coding_assignment_3.data.fruit

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FruitDao {

    @Query("SELECT * FROM fruit ORDER BY name ASC")
    fun getFruits(): Flow<List<Fruit>>

    @Query("SELECT * FROM fruit WHERE id = :id")
    fun getFruit(id: Int): Flow<Fruit>

    @Query("UPDATE fruit SET quantity_in_stock = quantity_in_stock + :amount WHERE id = :id")
    fun increaseAmount(id: Int, amount: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(fruit: Fruit)

    @Update
    suspend fun update(fruit: Fruit)

//    @Delete
//    suspend fun delete(fruit: Fruit)
}