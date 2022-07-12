package com.example.happybirthday

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.happybirthday.databinding.ActivityMainBinding
import com.example.happybirthday.donutmap.DonutMapFragment
import com.example.happybirthday.feed.FeedFragment
import com.example.happybirthday.rankyourdonuts.RankYourDonutsFragment

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.let { binding ->
            setContentView(binding.root)
            launchDonutMap()
            listenToBottomNavigation(binding = binding)
        }
    }

    fun listenToBottomNavigation(binding: ActivityMainBinding) {
        binding.bnvMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.donutMap -> {
                    launchDonutMap()
                }
                R.id.feed -> {
                    launchFeed()
                }
                R.id.donutRankings -> {
                    launchRankYourDonuts()
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    fun launchDonutMap() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcvMain, DonutMapFragment::class.java, null)
            .commit()
    }

    fun launchFeed() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcvMain, FeedFragment::class.java, null)
            .commit()
    }

    fun launchRankYourDonuts() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcvMain, RankYourDonutsFragment::class.java, null)
            .commit()
    }
}
