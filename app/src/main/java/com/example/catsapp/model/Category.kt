package com.example.catsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "category_table")
data class Category(
    @PrimaryKey @SerializedName("id")@ColumnInfo(name = "categoryId") var id : Int,
    @SerializedName("name") var name : String
)