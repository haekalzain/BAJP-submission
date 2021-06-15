package com.example.bjapsub1.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bjapsub1.databinding.ActivityMainBinding
import com.example.bjapsub1.presentation.adapter.ContentPagerAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityHomeBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        val sectionsPagerAdapter = ContentPagerAdapter(this, supportFragmentManager)
        activityHomeBinding.viewPager.adapter = sectionsPagerAdapter
        activityHomeBinding.tabs.setupWithViewPager(activityHomeBinding.viewPager)

        supportActionBar?.elevation = 0f
    }


}