package com.example.closedpull

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.closedpull.ui.main.ClosedPrFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ClosedPrFragment.newInstance())
                    .commitNow()
        }
    }
}