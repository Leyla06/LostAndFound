package com.example.lostfoundapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lostfoundapp.data.LostItemDao
import com.example.lostfoundapp.model.ItemType
import com.example.lostfoundapp.model.LostItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LostFoundViewModel(
    private val dao: LostItemDao
) : ViewModel() {

    val items: StateFlow<List<LostItem>> =
        dao.getAllItems()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    fun addItem(
        name: String,
        type: ItemType,
        description: String,
        location: String,
        imageUri: String?
    ) {
        viewModelScope.launch {
            val newItem = LostItem(
                name = name,
                type = type,
                description = description,
                location = location,
                imageUri = imageUri
            )
            dao.insertItem(newItem)
        }
    }
    fun resolveItem(id: Int) {
        viewModelScope.launch {
            dao.resolveItem(id)
        }
    }
    fun deleteItem(item: LostItem) {
        viewModelScope.launch {
            dao.deleteItem(item)
        }
    }
}