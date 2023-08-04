package com.example.maxmaven_assignment_gaurav.data.local

import androidx.room.Room
import com.example.maxmaven_assignment_gaurav.BookApplication

object DbHelper {

    fun getDB(): BookDatabase {
        return Room.databaseBuilder(
            BookApplication.INSTANCE.applicationContext,
            BookDatabase::class.java,
            name = "book.db"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}