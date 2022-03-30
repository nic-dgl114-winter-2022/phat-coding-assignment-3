package com.example.phat_coding_assignment_3.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.phat_coding_assignment_3.data.fruit.Fruit
import com.example.phat_coding_assignment_3.data.fruit.FruitDao
import com.example.phat_coding_assignment_3.data.land.Land
import com.example.phat_coding_assignment_3.data.land.LandDao
import com.example.phat_coding_assignment_3.data.user.User
import com.example.phat_coding_assignment_3.data.user.UserDao

@Database(entities = [Fruit::class, Land::class, User::class], version = 1, exportSchema = false)
abstract class MainRoomDatabase : RoomDatabase() {
    abstract fun fruitDao(): FruitDao
    abstract fun landDao(): LandDao
    abstract fun userDao(): UserDao

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
                    "app_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
//                    .createFromAsset("app_database.db")
                    .build()

                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}