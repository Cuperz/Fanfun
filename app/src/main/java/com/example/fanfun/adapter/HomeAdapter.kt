package com.example.fanfun.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class HomeAdapter(supportFragmentManager: FragmentManager, private var fragments: ArrayList<Fragment> = ArrayList()): FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    fun addFragment(fragment: Fragment){
        fragments.add(fragment)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Pendientes"
            1 -> "Borrador"
            else -> {
                return "Enviados"
            }
        }
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }
}