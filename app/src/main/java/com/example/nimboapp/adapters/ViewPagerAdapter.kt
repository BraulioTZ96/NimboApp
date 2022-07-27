package com.example.nimboapp.adapters

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nimboapp.fragments.MainFragment
import com.example.nimboapp.fragments.OptionsFragment
import com.example.nimboapp.fragments.ProfileFragment
import com.example.nimboapp.fragments.ShoppingFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity)  {

    companion object {

        private const val NUM_PAGES = 4

        const val MAIN_FRAGMENT = 0
        const val PROFILE_FRAGMENT = 1
        const val SHOPPING_FRAGMENT = 2
        const val OPTIONS_FRAGMENT = 3

    }

    override fun getItemCount() = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when(position){
            //0 -> {FragmentSpanish()}
            0 -> {MainFragment()}
            1 -> {ProfileFragment()}
            2 -> {ShoppingFragment()}
            3 -> {OptionsFragment()}
            else -> {throw Resources.NotFoundException("Position not found")}
        }
    }

}