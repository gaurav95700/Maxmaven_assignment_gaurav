package com.example.maxmaven_assignment_gaurav.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.maxmaven_assignment_gaurav.data.model.BookModel

@Database(entities = [BookModel::class], version = 1)

abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
}