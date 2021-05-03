package com.example.meetingapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.meetingapp.fragments.HomeFragment
import com.example.meetingapp.fragments.PersonalFragment

class ViewPagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> {
                return HomeFragment()
            }1 -> {
                return PersonalFragment()
            }
            else -> {
                return HomeFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> {
                "Home"
            }
            1 -> {
                "Personal"
            }
            else -> {
                "Home"
            }
        }
    }

}