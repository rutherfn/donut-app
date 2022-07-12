package com.example.happybirthday.donutmap

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.happybirthday.R
import com.example.happybirthday.databinding.FragmentDonutMapBinding

class DonutMapFragment : Fragment(R.layout.fragment_donut_map) {

    private var binding: FragmentDonutMapBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDonutMapBinding.bind(view)
    }
}
