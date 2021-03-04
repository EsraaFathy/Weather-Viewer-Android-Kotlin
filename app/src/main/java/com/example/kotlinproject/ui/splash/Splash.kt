package com.example.kotlinproject.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivitySplashBinding
import com.example.kotlinproject.ui.baseHome.MainActivity


class Splash : AppCompatActivity() {
    lateinit var binding:ActivitySplashBinding
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        val down=AnimationUtils.loadAnimation(this,R.animator.move_down)
        val animation :Animation=AnimationUtils.loadAnimation(this,R.anim.wave)
        binding.snow1.startAnimation(animation)
        binding.snow1.startAnimation(down)
        binding.snow2.startAnimation(animation)
        binding.snow2.startAnimation(down)
        binding.snow3.startAnimation(animation)
        binding.snow3.startAnimation(down)
        binding.snow4.startAnimation(animation)
        binding.snow4.startAnimation(down)
        binding.snow5.startAnimation(animation)
        binding.snow5.startAnimation(down)
        binding.snow6.startAnimation(animation)
        binding.snow6.startAnimation(down)
        binding.snow7.startAnimation(animation)
        binding.snow7.startAnimation(down)
        binding.snow8.startAnimation(animation)
        binding.snow8.startAnimation(down)
        binding.snow9.startAnimation(animation)
        binding.snow9.startAnimation(down)
        binding.snow10.startAnimation(animation)
        binding.snow10.startAnimation(down)
        binding.snow11.startAnimation(animation)
        binding.snow11.startAnimation(down)
        binding.snow12.startAnimation(animation)
        binding.snow12.startAnimation(down)

        val handler = Handler()
        handler.postDelayed(Runnable {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)
    }
}