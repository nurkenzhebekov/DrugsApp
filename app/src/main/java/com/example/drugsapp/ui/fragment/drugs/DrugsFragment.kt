package com.example.drugsapp.ui.fragment.drugs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.drugsapp.R
import com.example.drugsapp.databinding.FragmentDrugsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DrugsFragment : Fragment() {

    private lateinit var binding: FragmentDrugsBinding
    private lateinit var drugsViewModel: DrugsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDrugsBinding.inflate(layoutInflater)
        drugsViewModel = ViewModelProvider(this).get(DrugsViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val drugsVPAdapter = DrugsVPAdapter(this)
        binding.viewPager.adapter = drugsVPAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Green"
                    observeDrugs("Green", tab, R.drawable.ic_tab_green)
                }
                1 -> {
                    tab.text = "Red"
                    observeDrugs("Red", tab, R.drawable.ic_tab_red)
                }
                2 -> {
                    tab.text = "Orange"
                    observeDrugs("Orange", tab, R.drawable.ic_tab_orange)
                }
            }
        }.attach()
    }

    private fun observeDrugs(importance: String, tab: TabLayout.Tab, defaultIconRes: Int) {
        drugsViewModel.getDrugsByImportance(importance).observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                tab.icon = ContextCompat.getDrawable(requireContext(), defaultIconRes)
            } else {
                tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_tab_black)
            }
        })
    }
}