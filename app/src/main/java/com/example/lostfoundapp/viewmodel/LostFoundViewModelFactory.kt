package com.example.lostfoundapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lostfoundapp.data.LostItemDao

class LostFoundViewModelFactory(
    private val dao: LostItemDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(LostFoundViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LostFoundViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}