package com.example.phat_coding_assignment_3.data.fruit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Fruit(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name="name")
    val fruitName: String,
    @ColumnInfo(name="imageResourceId")
    val fruitImageResourceId: Int,
    @ColumnInfo(name="price")
    val fruitPrice: Int,
    @ColumnInfo(name="quantity_in_stock")
    val fruitQuantityInStock: Int,
)


