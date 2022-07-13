package com.example.happybirthday.splash

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.happybirthday.R
import com.example.happybirthday.TYPEWRITER_CHARACTER_DELAY
import com.example.happybirthday.TYPEWRITER_SECOND_START_DURATION
import com.example.happybirthday.databinding.FragmentDonutMapBinding
import com.example.happybirthday.databinding.FragmentSplashBinding

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private var binding: FragmentSplashBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSplashBinding.bind(view)
        binding?.let { bind ->
            main(binding = bind)
        }
    }

    private fun main(binding: FragmentSplashBinding) {
        setFontOfText(binding = binding)
       // splashTimer()
        startDonutAnimation(binding = binding)
        animateText(binding = binding)
    }

    private fun setFontOfText(binding: FragmentSplashBinding) {
        val type = Typeface.createFromAsset(requireContext().assets, "fonts/chewy.ttf")
        binding.tvAppName.typeface = type
        binding.twLoadingYourDonuts.typeface = type
        binding.twLoadingYourDonuts2.typeface = type
    }

    private fun startDonutAnimation(binding: FragmentSplashBinding) {
        binding.ivDonut.animation = AnimationUtils.loadAnimation(requireContext(), R.anim.donut_rotate_animation)
    }

    private fun animateText(binding: FragmentSplashBinding) = animateFirstLoadingText(binding = binding)

    private fun animateFirstLoadingText(binding: FragmentSplashBinding) {
        binding.twLoadingYourDonuts.setCharacterDelay(TYPEWRITER_CHARACTER_DELAY)
        binding.twLoadingYourDonuts.animateText(requireContext().getText(R.string.loading_your_donuts))

        Handler(Looper.getMainLooper()).postDelayed({
            animateSecondLoadingText(binding = binding)
        }, TYPEWRITER_SECOND_START_DURATION)
    }

    private fun animateSecondLoadingText(binding: FragmentSplashBinding) {
        binding.twLoadingYourDonuts.visibility = View.GONE
        binding.twLoadingYourDonuts2.visibility = View.VISIBLE
        binding.twLoadingYourDonuts2.setCharacterDelay(TYPEWRITER_CHARACTER_DELAY)
        binding.twLoadingYourDonuts2.animateText(requireContext().getText(R.string.loading_your_donuts))
    }
}