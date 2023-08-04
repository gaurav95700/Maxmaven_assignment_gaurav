package com.example.maxmaven_assignment_gaurav

import com.example.maxmaven_assignment_gaurav.data.model.BookModel
import com.example.maxmaven_assignment_gaurav.data.model.VolumeInfo
import com.google.gson.annotations.SerializedName


data class BookApiModel(
    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo,

    @SerializedName("bookModel")
    val bookModel: BookModel,
)