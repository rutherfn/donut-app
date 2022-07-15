package com.example.donutApp.donutmap

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.donutApp.R
import com.example.donutApp.databinding.FragmentDonutMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng

class DonutMapFragment : Fragment(R.layout.fragment_donut_map) {

    private var binding: FragmentDonutMapBinding? = null
    private var maps: GoogleMap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDonutMapBinding.bind(view)
        binding?.let { binding ->
            checkLocationPermission(binding = binding, savedInstanceState = savedInstanceState)
        }
    }

    fun checkLocationPermission(binding: FragmentDonutMapBinding, savedInstanceState: Bundle?) {
        context?.let { context ->
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                initializeMap(binding, savedInstanceState)
            } else {
//                requestPermissions(
//                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun initializeMap(binding: FragmentDonutMapBinding, savedInstanceState: Bundle?) {
        binding.mvDonutShopMap.onCreate(savedInstanceState)
        binding.mvDonutShopMap.getMapAsync {
            maps = it
            maps?.let { googleMap ->
                println("gettt")
                googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN)
                googleMap.isMyLocationEnabled = true
                googleMap.uiSettings.isMyLocationButtonEnabled = true
                val latLng = LatLng(43.041069, -87.909416)
                val yourLocation = CameraUpdateFactory.newLatLngZoom(latLng, 5f)
                googleMap.animateCamera(yourLocation)
                try {
                    context?.let { context ->
                        MapsInitializer.initialize(context)
                        println("$googleMap.")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
