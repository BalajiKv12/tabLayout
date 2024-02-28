package com.example.tablayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val fragments : ArrayList<Fragment>) : FragmentStateAdapter(fragmentManager,lifecycle) {

    override fun getItemCount(): Int {
        //returning the size
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        //returning the fragment
        return fragments.get(position)
    }

    fun deleteFragment(positiom: Int){
        //deleting the fragment
        fragments.removeAt(positiom)
    }
}