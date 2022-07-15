package com.example.donutApp.donutmap

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.donutApp.R
import com.example.donutApp.databinding.FragmentDonutMapBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import java.lang.Exception

class DonutMapFragment : Fragment(R.layout.fragment_donut_map), OnMapReadyCallback {

    private var binding: FragmentDonutMapBinding? = null
    private var maps: GoogleMap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDonutMapBinding.bind(view)
        binding?.let { binding ->
            initializeMap(binding = binding, savedInstanceState = savedInstanceState)
        }
    }

    fun checkLocationPermission (binding: FragmentDonutMapBinding) {

    }

    fun initializeMap(binding: FragmentDonutMapBinding, savedInstanceState: Bundle?) {
        binding.mvDonutShopMap.onCreate(savedInstanceState)
        binding.mvDonutShopMap.getMapAsync {
            maps = it
            maps?.let { googleMap ->
                googleMap.isMyLocationEnabled = true
                googleMap.uiSettings.isMyLocationButtonEnabled = true
                try {
                    context?.let { context ->
                        MapsInitializer.initialize(context)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        println("on map ready")
    }
}
