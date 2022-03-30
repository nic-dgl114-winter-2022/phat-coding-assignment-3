package com.example.phat_coding_assignment_3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.phat_coding_assignment_3.data.fruit.Fruit
import com.example.phat_coding_assignment_3.data.fruit.FruitDao
import com.example.phat_coding_assignment_3.data.land.Land
import com.example.phat_coding_assignment_3.data.land.LandDao

@Database(entities = arrayOf(Fruit::class, Land::class), version = 2, exportSchema = false)
abstract class MainRoomDatabase : RoomDatabase() {
    abstract fun fruitDao(): FruitDao
    abstract fun landDao(): LandDao

    companion object {
        @Volatile
        private var INSTANCE: MainRoomDatabase? = null

        fun getDatabase(context: Context): MainRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainRoomDatabase::class.java,
                    "farming_game"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}