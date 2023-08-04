package com.example.maxmaven_assignment_gaurav.data.repository

import com.example.maxmaven_assignment_gaurav.data.api.NetworkService
import com.example.maxmaven_assignment_gaurav.data.api.RetrofitBuilder
import com.example.maxmaven_assignment_gaurav.data.local.BookDatabase
import com.example.maxmaven_assignment_gaurav.data.local.DbHelper
import com.example.maxmaven_assignment_gaurav.data.model.BookModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class BookRepository {

    private val networkService: NetworkService = RetrofitBuilder.networkService

    private val bookDatabase: BookDatabase = DbHelper.getDB()

    suspend fun getBookData(): Flow<List<BookModel>> {
        return flow {
            emit(networkService.getBookData())
        }.map {
            it.items.map { bookApiModel ->
                BookModel(
                    thumbnail = bookApiModel.volumeInfo.imageLinks?.thumbnail.orEmpty(),
                    title = bookApiModel.volumeInfo.title,
                    description = bookApiModel.volumeInfo.publisher,
                    pageCount = bookApiModel.volumeInfo.pageCount,
                    authors = bookApiModel.volumeInfo.authors?.joinToString { ", " }.orEmpty()
                )
            }
        }
    }

    fun insertBookDataToLocalDb(bookModel: BookModel) {
        bookDatabase.bookDao().insertBook(bookModel)
    }

}