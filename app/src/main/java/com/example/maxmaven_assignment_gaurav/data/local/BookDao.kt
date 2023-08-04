package com.example.maxmaven_assignment_gaurav.data.local

import androidx.room.*
import com.example.maxmaven_assignment_gaurav.data.model.BookModel

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: BookModel)

    @Query("select * from bookmodel")
    fun getAllBooks(): List<BookModel>

    @Delete
    fun delete(book: BookModel)

}