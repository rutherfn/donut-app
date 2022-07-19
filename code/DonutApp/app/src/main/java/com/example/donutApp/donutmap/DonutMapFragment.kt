package com.example.donutApp.donutmap

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.donutApp.R
import com.example.donutApp.databinding.FragmentDonutMapBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DonutMapFragment : Fragment(R.layout.fragment_donut_map), OnMapReadyCallback {

    private var binding: FragmentDonutMapBinding? = null

    private var googleMap: GoogleMap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDonutMapBinding.bind(view)
        binding?.let { binding ->
            binding.mvDonutShopMap.onCreate(savedInstanceState)
            setUpMap()

            binding.mvDonutShopMap.onResume()
        }
    }

    override fun onResume() {
        super.onResume()
        binding?.mvDonutShopMap?.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()

        // We need to call MapView.onDestroy() when the view is destroyed,
        // but the binding object is being destroyed at the same time.
        // Save the MapView in its own object so that we can access it independently.
        binding?.mvDonutShopMap?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding?.mvDonutShopMap?.onLowMemory()
    }

    private fun setUpMap() {
        MapsInitializer.initialize(requireActivity())

        binding?.mvDonutShopMap?.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap?.mapType = GoogleMap.MAP_TYPE_NORMAL

        googleMap?.let { gMap ->
            gMap.uiSettings.isMapToolbarEnabled = false
            gMap.uiSettings.isZoomControlsEnabled = true

            val lat = -22.0
            val long = 161.0
            val sydney = LatLng(lat, long)
            gMap.addMarker(
                MarkerOptions().position(sydney)
                    .title("Marker in Sydney") // below line is use to add custom marker on our map.
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.donutmarker))
            )
            map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }
    }
}
