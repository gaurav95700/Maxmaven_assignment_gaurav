package com.example.maxmaven_assignment_gaurav.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "bookmodel")
data class BookModel(
    @ColumnInfo(name = "thumbnail")
    @SerializedName("thumbnail") val thumbnail: String,
    @PrimaryKey
    @ColumnInfo(name = "title")
    @SerializedName("title") val title: String,
    @ColumnInfo(name = "description")
    @SerializedName("description") val description: String,
    @ColumnInfo(name = "pageCount")
    @SerializedName("pageCount") val pageCount: String,
    @ColumnInfo(name = "authors")
    @SerializedName("authors") val authors: String,
)