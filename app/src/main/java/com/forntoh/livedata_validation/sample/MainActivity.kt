package com.forntoh.livedata_validation.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.forntoh.livedata_validation.sample.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) loadFragment(MainFragment())
    }

    fun loadFragment(fragment: Fragment) = supportFragmentManager.beginTransaction()
        .replace(R.id.container, fragment)
        .addToBackStack(fragment.javaClass.name)
        .commit()
}