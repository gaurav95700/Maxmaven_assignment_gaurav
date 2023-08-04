package com.example.maxmaven_assignment_gaurav.data.model

import com.google.gson.annotations.SerializedName

data class VolumeInfo(
    @SerializedName("title") val title: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("pageCount") val pageCount: String,
    @SerializedName("authors") val authors: List<String?>?,
    @SerializedName("imageLinks") val imageLinks: ImageLinks?,
)