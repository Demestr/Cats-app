package com.example.catsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cats_table")
data class Cat(
    @PrimaryKey @SerializedName("id")@ColumnInfo(name = "catId")var id: String,
    @SerializedName("url")var url: String,
    @Ignore @SerializedName("breeds")val breeds: List<Breed>?,
    @Ignore @SerializedName("categories")val categories: List<Category>?,
    @Ignore var isExistInRoom: Boolean = false
){
    constructor(id: String, url: String) : this(id, url, emptyList(), null, false)
}