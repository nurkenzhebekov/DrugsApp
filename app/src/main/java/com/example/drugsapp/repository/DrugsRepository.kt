package com.example.drugsapp.repository

import androidx.lifecycle.LiveData
import com.example.drugsapp.data.DrugsDao
import com.example.drugsapp.model.Drug

class DrugsRepository(private val drugsDao: DrugsDao) {

    fun getDrugsByImportance(importance: String): LiveData<List<Drug>> {
        return drugsDao.getDrugsByImportance(importance)
    }
}