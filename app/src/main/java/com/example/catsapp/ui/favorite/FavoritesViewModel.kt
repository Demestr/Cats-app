package com.example.catsapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.catsapp.database.CatsDao
import com.example.catsapp.model.dbclasses.CatWithBreedAndCategory
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(dao: CatsDao) : ViewModel() {

    val cats: LiveData<List<CatWithBreedAndCategory>> = dao.getCats()

}