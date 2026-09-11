package com.example.lostfoundapp.data

import androidx.room.TypeConverter
import com.example.lostfoundapp.model.ItemType

class ItemTypeConverter {
    @TypeConverter
    fun fromItemType(type: ItemType): String {
        return type.name
    }

    @TypeConverter
    fun toItemType(value: String): ItemType {
        return ItemType.valueOf(value)
    }
}