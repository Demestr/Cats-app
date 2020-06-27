package com.example.catsapp.ui.favorite

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.catsapp.database.CatsDao
import com.example.catsapp.database.CatsDatabase
import com.example.catsapp.model.dbclasses.CatWithBreedAndCategory

class FavoritesViewModel(context: Context) : ViewModel() {
    private val dao: CatsDao = CatsDatabase.getInstance(context).getDao()
    val cats: LiveData<List<CatWithBreedAndCategory>>

    init {
        cats = dao.getCats()
    }
}