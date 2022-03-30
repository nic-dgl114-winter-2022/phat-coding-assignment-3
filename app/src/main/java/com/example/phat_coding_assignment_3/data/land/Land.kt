package com.example.phat_coding_assignment_3.data.land

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Land (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name="name")
    val landName: String,
    @ColumnInfo(name="status")
    val landStatus: String,
    @ColumnInfo(name="fruit_id")
    val fruitId: Int,
    @ColumnInfo(name="harvest_amount")
    val harvestAmount: Int,
//    @ColumnInfo(name="seconds_to_harvest")
//    val secondsToHarvest: Int,
)