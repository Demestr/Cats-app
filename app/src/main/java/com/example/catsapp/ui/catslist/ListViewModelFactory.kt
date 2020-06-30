package com.example.catsapp.ui.catslist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catsapp.database.CatsDao
import com.example.catsapp.network.ServiceApi
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ListViewModelFactory @Inject constructor(private val context: Context, private val api: ServiceApi, private val dao: CatsDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(context, api, dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}