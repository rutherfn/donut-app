package com.example.donutApp.donutmap

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.donutApp.R
import com.example.donutApp.databinding.FragmentDonutMapBinding

class DonutMapFragment : Fragment(R.layout.fragment_donut_map) {

    private var binding: FragmentDonutMapBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDonutMapBinding.bind(view)
    }
}
