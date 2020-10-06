package com.github.forntoh.livedata_validation.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.forntoh.livedata_validation.sample.ui.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment())
                .commitNow()
        }
    }
}