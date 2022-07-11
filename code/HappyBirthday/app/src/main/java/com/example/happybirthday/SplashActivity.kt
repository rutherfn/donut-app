package com.example.happybirthday

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.happybirthday.databinding.FragmentSplashBinding

const val SPLASH_TIMER_DURATION = 4500L
const val TYPEWRITER_CHARACTER_DELAY = 150L

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = FragmentSplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        splashTimer()
        animateLoadingText(binding = binding)
    }

    private fun splashTimer() {
        Handler(Looper.getMainLooper()).postDelayed({
            startMainScreen()
        }, SPLASH_TIMER_DURATION)
    }

    private fun startMainScreen() {
        val splashActivity = this
        val intent = Intent(splashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun animateLoadingText(binding: FragmentSplashBinding) {
        binding.twLoadingYourDonuts.setCharacterDelay(TYPEWRITER_CHARACTER_DELAY)
        binding.twLoadingYourDonuts.animateText(application.getText(R.string.loading_your_donuts))
    }
}
