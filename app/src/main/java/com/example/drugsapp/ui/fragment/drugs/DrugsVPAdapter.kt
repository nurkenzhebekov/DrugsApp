package com.example.drugsapp.ui.fragment.drugs

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DrugsVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DrugsListFragment.newInstance("Green")
            1 -> DrugsListFragment.newInstance("Red")
            2 -> DrugsListFragment.newInstance("Orange")
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}