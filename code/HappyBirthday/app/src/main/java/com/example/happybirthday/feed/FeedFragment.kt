package com.example.happybirthday.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.happybirthday.R
import com.example.happybirthday.databinding.FragmentFeedBinding

class FeedFragment : Fragment(R.layout.fragment_feed) {

    private var binding: FragmentFeedBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentFeedBinding.bind(view)
    }
}