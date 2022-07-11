package com.example.happybirthday

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.happybirthday.databinding.FragmentSplashBinding

const val SPLASH_TIMER_DURATION = 6000L
const val TYPEWRITER_CHARACTER_DELAY = 90L
const val TYPEWRITER_SECOND_START_DURATION = 2880L

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = FragmentSplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        main(binding = binding)
    }

    private fun main(binding: FragmentSplashBinding) {
        splashTimer()
        startDonutAnimation(binding = binding)
        animateText(binding = binding)
    }

    private fun splashTimer() {
        Handler(Looper.getMainLooper()).postDelayed({
            startMainScreen()
        }, SPLASH_TIMER_DURATION)
    }

    private fun startMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startDonutAnimation(binding: FragmentSplashBinding) {
        binding.ivDonut.animation = AnimationUtils.loadAnimation(this, R.anim.donut_rotate_animation)
    }

    private fun animateText(binding: FragmentSplashBinding) = animateFirstLoadingText(binding = binding)

    private fun animateFirstLoadingText(binding: FragmentSplashBinding) {
        binding.twLoadingYourDonuts.setCharacterDelay(TYPEWRITER_CHARACTER_DELAY)
        binding.twLoadingYourDonuts.animateText(application.getText(R.string.loading_your_donuts))

        Handler(Looper.getMainLooper()).postDelayed({
            animateSecondLoadingText(binding = binding)
        }, TYPEWRITER_SECOND_START_DURATION)
    }

    private fun animateSecondLoadingText(binding: FragmentSplashBinding) {
        binding.twLoadingYourDonuts.visibility = View.GONE
        binding.twLoadingYourDonuts2.visibility = View.VISIBLE
        binding.twLoadingYourDonuts2.setCharacterDelay(TYPEWRITER_CHARACTER_DELAY)
        binding.twLoadingYourDonuts2.animateText(application.getText(R.string.loading_your_donuts))
    }
}
