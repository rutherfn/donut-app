package com.example.happybirthday.rankyourdonuts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.happybirthday.R
import com.example.happybirthday.databinding.FragmentRankYourDonutsBinding

class RankYourDonutsFragment : Fragment(R.layout.fragment_rank_your_donuts) {
    private var binding: FragmentRankYourDonutsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentRankYourDonutsBinding.bind(view)
    }
}
