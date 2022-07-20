package com.example.donutApp.donutmap

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.donutApp.R
import com.example.donutApp.data.DonutShopMapLocation
import com.example.donutApp.databinding.FragmentDonutMapBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

const val FILE_NAME = "donut_locations.json"

class DonutMapFragment : Fragment(R.layout.fragment_donut_map), OnMapReadyCallback {

    private var binding: FragmentDonutMapBinding? = null

    private var googleMap: GoogleMap? = null

    private var donutShopMapLocationArrayList = arrayListOf<DonutShopMapLocation>()

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

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun plotMapMarkers(gMap: GoogleMap) {
        this.context?.let { context ->

            val jsonFileString = getJsonDataFromAsset(context = context, fileName = FILE_NAME)

            val gson = Gson()
            val donutShopMapLocations: List<DonutShopMapLocation> = gson.fromJson(jsonFileString, object : TypeToken<List<DonutShopMapLocation>>() {}.type)

            donutShopMapLocations.forEach { donutShopMapLocation ->
                donutShopMapLocation.lat?.let { lat ->
                    donutShopMapLocation.long?.let { long ->
                        donutShopMapLocation.donutShopName?.let { donutShopName ->
                            val latLng = LatLng(lat.toDouble(), long.toDouble())
                            gMap.addMarker(
                                MarkerOptions().position(latLng)
                                    .title(donutShopName) // below line is use to add custom marker on our map.
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.donutmarker))
                            )
                            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 2.5f))
                        }
                    }
                }
            }
        }
    }

    fun test(gMap: GoogleMap, latLng: LatLng) {
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 2.5f))
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap?.mapType = GoogleMap.MAP_TYPE_NORMAL

        googleMap?.let { gMap ->
            gMap.uiSettings.isMapToolbarEnabled = false
            gMap.uiSettings.isZoomControlsEnabled = true

            plotMapMarkers(gMap = gMap)

            gMap.setOnMarkerClickListener { marker ->
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.position, 22.5f))

                false
            }
        }
    }
}
