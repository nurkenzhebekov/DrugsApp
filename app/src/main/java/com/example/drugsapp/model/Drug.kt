package com.example.drugsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drugs")
data class Drug(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val time: String,
    val importance: String
)