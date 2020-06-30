package com.example.catsapp.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catsapp.database.CatsDao
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class FavoritesViewModelFactory @Inject constructor(private val dao: CatsDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            return FavoritesViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}