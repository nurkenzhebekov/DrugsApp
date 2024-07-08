package com.example.drugsapp.ui.fragment.drugs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drugsapp.R
import com.example.drugsapp.databinding.FragmentDrugsListBinding
import com.example.drugsapp.model.Drug
import com.example.drugsapp.ui.fragment.dialog.AddDrugDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DrugsListFragment : Fragment() {

    private lateinit var binding: FragmentDrugsListBinding
    private lateinit var viewModel: DrugsViewModel
    private lateinit var adapter: DrugsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDrugsListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DrugsViewModel::class.java)
        adapter = DrugsAdapter()

        binding.rvDrugs.adapter = adapter
        binding.rvDrugs.layoutManager = LinearLayoutManager(requireContext())

        val importance = arguments?.getString("importance") ?: "Green"
        viewModel.getDrugsByImportance(importance).observe(viewLifecycleOwner) { drugs ->
            adapter.submitList(drugs)
        }

        binding.flacbtAddDrug.setOnClickListener {
            val dialog = AddDrugDialogFragment()
            dialog.show(parentFragmentManager, "AddDrugDialogFragment")
        }
        /*val fab: FloatingActionButton = view.findViewById(R.id.flacbt_add_drug)
        fab.setOnClickListener {
            val dialogFragment = AddDrugDialogFragment()
            dialogFragment.setTargetFragment(this, 0)
            dialogFragment.show(parentFragmentManager, "AddDrugDialogFragment")
        }*/
    }

    fun addDrug(drug: Drug) {
        viewModel.insert(drug)
    }

    companion object {
        fun newInstance(importance: String): DrugsListFragment {
            val fragment = DrugsListFragment()
            val args = Bundle()
            args.putString("importance", importance)
            fragment.arguments = args
            return fragment
        }
    }
}