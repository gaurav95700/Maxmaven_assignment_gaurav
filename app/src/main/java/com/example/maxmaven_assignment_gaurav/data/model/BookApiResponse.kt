package com.example.maxmaven_assignment_gaurav.data.model

import com.example.maxmaven_assignment_gaurav.BookApiModel
import com.google.gson.annotations.SerializedName


data class BookApiResponse(
    @SerializedName("items")
    val items: List<BookApiModel> = ArrayList(),
)
