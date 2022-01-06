package com.example.bv_connect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.app.TaskStackBuilder
import android.content.Intent
import android.view.MenuItem
import com.example.bv_connect.club.ui.MainActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()

        //initToolbar()

        //initDataBinding()

        initActions()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    //region Init Functions
    private fun initData() {

    }


   /* private fun initDataBinding() {

        Utils.setImageToImageView(this, promotionImageView, R.drawable.education_img_9)

        Utils.setImageToImageView(this, stationaryImageView, R.drawable.education_home2_img_1)
        Utils.setImageToImageView(this, booksImageView, R.drawable.education_home2_img_2)
        Utils.setImageToImageView(this, tutorImageView, R.drawable.education_home2_img_3)
        Utils.setImageToImageView(this, institutionImageView, R.drawable.education_home2_img_4)

    }*/

    private fun initActions() {

        stationaryImageView.setOnClickListener {
            TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(Intent(this, SplashActivity::class.java))
                .addNextIntent(Intent(this, MainActivity::class.java))
                .startActivities()
        }

        booksImageView.setOnClickListener {
            TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(Intent(this, SplashActivity::class.java))
                .addNextIntent(Intent(this, AskReply::class.java))
                .startActivities()
        }

        tutorImageView.setOnClickListener {
            TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(Intent(this, SplashActivity::class.java))
                .addNextIntent(Intent(this, Shop::class.java))
                .startActivities()
        }

        institutionImageView.setOnClickListener {
            TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(Intent(this, SplashActivity::class.java))
                .addNextIntent(Intent(this, Chatroom::class.java))
                .startActivities()
        }


    }


}