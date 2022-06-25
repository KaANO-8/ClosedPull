package com.kaano8.closedpull

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kaano8.closedpull.R
import com.kaano8.closedpull.ui.main.ClosedPrFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClosedPrActivity : AppCompatActivity() {

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