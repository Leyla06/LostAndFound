package com.example.lostfoundapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lostfoundapp.model.LostItem
import kotlinx.coroutines.flow.Flow

@Dao
interface LostItemDao {
    @Query("SELECT * FROM lost_items ORDER BY createdAt DESC")
    fun getAllItems(): Flow<List<LostItem>>
    @Insert
    suspend fun insertItem(item: LostItem): Long
    @Query("UPDATE lost_items SET isResolved = 1 WHERE id = :id")
    suspend fun resolveItem(id: Int): Int
    @Delete
    suspend fun deleteItem(item: LostItem): Int
}