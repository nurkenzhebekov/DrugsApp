package com.example.drugsapp.ui.fragment.drugs

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.drugsapp.data.DrugsDatabase
import com.example.drugsapp.databinding.ItemDrugsBinding
import com.example.drugsapp.model.Drug
import com.example.drugsapp.repository.DrugsRepository
import kotlinx.coroutines.launch

class DrugsViewModel(app: Application) : AndroidViewModel(app) {
    private val repository: DrugsRepository

    init {
        val drugsDao = DrugsDatabase.getDatabase(app).drugsDao()
        repository = DrugsRepository(drugsDao)
    }

    fun getDrugsByImportance(importance: String): LiveData<List<Drug>> {
        return repository.getDrugsByImportance(importance)
    }

    fun insert(drug: Drug) = viewModelScope.launch {
        repository.insert(drug)
    }
}

class DrugsAdapter : ListAdapter<Drug, DrugsAdapter.MedicationViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Drug>() {
            override fun areItemsTheSame(oldItem: Drug, newItem: Drug) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Drug, newItem: Drug) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicationViewHolder {
        val binding = ItemDrugsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MedicationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MedicationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MedicationViewHolder(private val binding: ItemDrugsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(medication: Drug) {
            binding.tvTitle.text = medication.title
            binding.tvDescription.text = medication.description
            binding.tvTime.text = medication.time
        }
    }
}