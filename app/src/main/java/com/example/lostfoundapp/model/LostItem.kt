package com.example.lostfoundapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class ItemType{
    LOST,
    FOUND
}
@Entity(tableName = "lost_items")
data class LostItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val type: ItemType,
    val description: String,
    val location: String,
    val imageUri: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val isResolved: Boolean = false
)