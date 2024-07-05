package com.example.drugsapp.ui.fragment.drugs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.drugsapp.R
import com.example.drugsapp.databinding.FragmentDrugsBinding
import com.google.android.material.tabs.TabLayoutMediator

class DrugsFragment : Fragment() {

    private lateinit var binding: FragmentDrugsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDrugsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val drugsVPAdapter = DrugsVPAdapter(this)
        binding.viewPager.adapter = drugsVPAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.icon = when (position) {
                0 -> ContextCompat.getDrawable(requireContext(), R.drawable.ic_tab_green)
                1 -> ContextCompat.getDrawable(requireContext(), R.drawable.ic_tab_red)
                else -> ContextCompat.getDrawable(requireContext(), R.drawable.ic_tab_orange)
            }
            tab.text = when (position) {
                0 -> "Green"
                1 -> "Red"
                else -> "Orange"
            }
        }.attach()
    }
}