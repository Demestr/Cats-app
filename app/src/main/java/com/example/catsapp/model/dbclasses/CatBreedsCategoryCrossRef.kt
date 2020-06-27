package com.example.catsapp.model.dbclasses

import androidx.room.Entity

@Entity(primaryKeys = ["catId", "breedId", "categoryId"])
data class CatBreedsCategoryCrossRef(
    val catId: String,
    val breedId: String,
    val categoryId: Int
)