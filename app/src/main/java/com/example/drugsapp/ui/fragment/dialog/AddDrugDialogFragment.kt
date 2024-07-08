package com.example.drugsapp.ui.fragment.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isNotEmpty
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.drugsapp.R
import com.example.drugsapp.databinding.FragmentAddDrugDialogBinding
import com.example.drugsapp.model.Drug
import com.example.drugsapp.ui.fragment.drugs.DrugsListFragment
import com.example.drugsapp.ui.fragment.drugs.DrugsViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AddDrugDialogFragment : DialogFragment() {

    private var _binding: FragmentAddDrugDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var drugsViewModel: DrugsViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentAddDrugDialogBinding.inflate(layoutInflater)
        drugsViewModel = ViewModelProvider(this).get(DrugsViewModel::class.java)

        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setView(binding.root)

        val importanceAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.importance_levels,
            android.R.layout.simple_spinner_item
        )
        importanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        binding.spinnerImportance.adapter = importanceAdapter

        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.spinnerImportance.visibility = View.VISIBLE
                binding.btDelay.visibility = View.VISIBLE
            } else {
                binding.spinnerImportance.visibility = View.GONE
                binding.btDelay.visibility = View.GONE
            }
        }

        binding.imgBtClose.setOnClickListener {
            dismiss()
        }

        binding.btAdd.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val description = binding.edtDescription.text.toString()
            val time = binding.edtTime.text.toString()
            val importance = if (binding.checkbox.isChecked) binding.spinnerImportance.selectedItem else "Green"

            if (title.isNotEmpty() && description.isNotEmpty() && time.isNotEmpty()) {
                drugsViewModel.insert(Drug(title = title, description = description, time = time, importance = importance.toString()))
                dismiss()
            } else {
                Toast.makeText(requireContext(), "Please, fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btChange.setOnClickListener {
            dismiss()
        }

        return builder.create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}