package com.example.maxmaven_assignment_gaurav.data.api

import com.example.maxmaven_assignment_gaurav.data.model.BookApiResponse
import com.example.maxmaven_assignment_gaurav.utils.Constants.BOOK_URL
import retrofit2.http.GET
import retrofit2.http.Url

interface NetworkService {

    @GET
    suspend fun getBookData(@Url url: String = BOOK_URL): BookApiResponse

}