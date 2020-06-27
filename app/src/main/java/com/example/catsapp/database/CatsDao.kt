package com.example.catsapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.catsapp.model.Breed
import com.example.catsapp.model.Cat
import com.example.catsapp.model.Category
import com.example.catsapp.model.dbclasses.CatWithBreedAndCategory

@Dao
interface CatsDao {

    @Transaction
    suspend fun insertAll(cat: Cat){
        insertCat(cat)
        cat.breeds?.let { list -> list.forEach { insertBreed(it) }}
        cat.categories?.let { list -> list.forEach { insertCategory(it)} }
    }


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCat(cat: Cat)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBreed(breed: Breed)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: Category)

    @Delete
    suspend fun deleteCat(cat: Cat)

    @Query(value = "SELECT * FROM cats_table")
    fun getCats(): LiveData<List<CatWithBreedAndCategory>>

    @Query(value = "SELECT * FROM breeds_table")
    fun getBreeds(): LiveData<List<Breed>>
}