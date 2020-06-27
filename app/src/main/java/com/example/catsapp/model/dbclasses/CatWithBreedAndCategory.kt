package com.example.catsapp.model.dbclasses

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.catsapp.model.Breed
import com.example.catsapp.model.Cat
import com.example.catsapp.model.Category

data class CatWithBreedAndCategory(
    @Embedded val cat: Cat,
    @Relation(
        parentColumn = "catId",
        entityColumn = "breedId",
        associateBy = Junction(CatBreedsCategoryCrossRef::class)
    )
    val breeds: List<Breed>,
    @Relation(
        parentColumn = "catId",
        entityColumn = "categoryId",
        associateBy = Junction(CatBreedsCategoryCrossRef::class)
    )
    val categories: List<Category>
)