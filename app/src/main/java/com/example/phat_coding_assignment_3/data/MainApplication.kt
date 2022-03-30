package com.example.phat_coding_assignment_3.data

import android.app.Application

class MainApplication : Application() {
    // Using by lazy so the database is only created when needed
    // rather than when the application starts
    val database: MainRoomDatabase by lazy { MainRoomDatabase.getDatabase(this) }
}
