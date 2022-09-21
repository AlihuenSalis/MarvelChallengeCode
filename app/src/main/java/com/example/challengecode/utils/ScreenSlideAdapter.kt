package com.example.challengecode.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.challengecode.ui.view.fragments.CharacterFragment
import com.example.challengecode.ui.view.fragments.EventFragment

class ScreenSlideAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CharacterFragment()
            else -> EventFragment()
        }
    }

}