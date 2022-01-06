package com.example.bv_connect

import android.app.TaskStackBuilder
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.bv_connect.club.ui.MainActivity


class SplashActivity : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
        TaskStackBuilder.create(this)
            .addNextIntentWithParentStack(Intent(this, SplashActivity::class.java))
            .addNextIntent(Intent(this, com.example.bv_connect.MainActivity::class.java))
            .startActivities()
    }
}