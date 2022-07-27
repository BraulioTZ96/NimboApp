package com.example.nimboapp.activities

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import com.example.nimboapp.R
import com.example.nimboapp.adapters.ViewPagerAdapter
import com.example.nimboapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tabViewpager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabsMainMenu,binding.tabViewpager){ tab, index ->
//            tab.text = when(index){
//                0 -> {"Spanish"}
//                1 -> {"Reggeaton"}
//                2 -> {"Pop"}
//                else -> {throw Resources.NotFoundException("Position not found")}
//            }
            tab.icon = when(index){
                ViewPagerAdapter.MAIN_FRAGMENT -> {AppCompatResources.getDrawable(this, R.drawable.ic_outline_home_32)}
                ViewPagerAdapter.PROFILE_FRAGMENT -> {AppCompatResources.getDrawable(this, R.drawable.ic_outline_account_circle_32)}
                ViewPagerAdapter.SHOPPING_FRAGMENT -> {AppCompatResources.getDrawable(this, R.drawable.ic_outline_shopping_cart_32)}
                ViewPagerAdapter.OPTIONS_FRAGMENT -> {AppCompatResources.getDrawable(this, R.drawable.ic_outline_menu_32)}
                else -> {throw Resources.NotFoundException("Position not found")}
            }
        }.attach()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}