package com.example.drugsapp.ui.fragment.drugs

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.drugsapp.data.DrugsDatabase
import com.example.drugsapp.databinding.ItemDrugsBinding
import com.example.drugsapp.model.Drug
import com.example.drugsapp.repository.DrugsRepository

class DrugsViewModel(app: Application) : AndroidViewModel(app) {
    private val repository: DrugsRepository

    init {
        val drugsDao = DrugsDatabase.getDatabase(app).drugsDao()
        repository = DrugsRepository(drugsDao)
    }

    fun getDrugsByImportance(importance: String): LiveData<List<Drug>> {
        return repository.getDrugsByImportance(importance)
    }
}

class DrugsAdapter : ListAdapter<Drug, DrugsAdapter.DrugViewHolder>(DrugDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrugViewHolder {
        val binding = ItemDrugsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DrugViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DrugViewHolder, position: Int) {
        val drug = getItem(position)
        holder.bind(drug)
    }

    class DrugViewHolder(private val binding: ItemDrugsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(drug: Drug) {
            binding.tvTitleTx.text = drug.title
            binding.tvDescriptionTx.text = drug.description
            binding.tvTimeTx.text = drug.time
        }
    }

    class DrugDiffCallback : DiffUtil.ItemCallback<Drug>() {
        override fun areItemsTheSame(oldItem: Drug, newItem: Drug): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Drug, newItem: Drug): Boolean {
            return oldItem == newItem
        }
    }
}