package com.example.drugsapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drugsapp.model.Drug

@Dao
interface DrugsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(drug: Drug)

    @Query("SELECT * FROM drugs WHERE importance = :importance")
    fun getDrugsByImportance(importance: String): LiveData<List<Drug>>
}