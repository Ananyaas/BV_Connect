package com.example.bv_connect

import android.app.TaskStackBuilder
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
        TaskStackBuilder.create(this)
            .addNextIntentWithParentStack(Intent(this, SplashActivity::class.java))
            .addNextIntent(Intent(this, LoginActivity::class.java))
            .startActivities()
    }
}