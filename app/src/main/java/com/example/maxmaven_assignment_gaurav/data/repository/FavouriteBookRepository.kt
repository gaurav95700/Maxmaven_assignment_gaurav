package com.example.maxmaven_assignment_gaurav.data.repository

import com.example.maxmaven_assignment_gaurav.data.local.BookDatabase
import com.example.maxmaven_assignment_gaurav.data.local.DbHelper
import com.example.maxmaven_assignment_gaurav.data.model.BookModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavouriteBookRepository {

    private val bookDatabase: BookDatabase = DbHelper.getDB()

    fun getBookDataFromLocalDb(): Flow<List<BookModel>> = flow {
        emit(bookDatabase.bookDao().getAllBooks())
    }

    fun deleteBook(bookModel: BookModel) {
        bookDatabase.bookDao().delete(bookModel)
    }

}