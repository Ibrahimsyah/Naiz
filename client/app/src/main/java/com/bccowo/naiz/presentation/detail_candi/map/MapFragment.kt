package com.bccowo.naiz.presentation.detail_candi.map

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bccowo.naiz.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {
    companion object {
        private const val EXTRA_LONGITUDE = "CANDI_LONGITUDE"
        private const val EXTRA_LATITUDE = "CANDI_LATITUDE"

        fun createInstance(longitude: Double, latitude: Double): MapFragment {
            return MapFragment().apply {
                arguments = Bundle().apply {
                    putDouble(EXTRA_LONGITUDE, longitude)
                    putDouble(EXTRA_LATITUDE, latitude)
                }
            }
        }
    }

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private var candiLongitude = 0.0
    private var candiLatitude = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            candiLongitude = it.getDouble(EXTRA_LONGITUDE)
            candiLatitude = it.getDouble(EXTRA_LATITUDE)
        }

        val mapFragment = binding.mapFragment
        mapFragment.onCreate(savedInstanceState)
        mapFragment.onResume()
        mapFragment.getMapAsync(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val candi = LatLng(candiLatitude, candiLongitude)
        googleMap.addMarker(
            MarkerOptions()
                .position(candi)
        )
        googleMap.setOnMapClickListener {
            val gmmIntentUri = Uri.parse("geo:$candiLatitude, $candiLongitude")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            activity?.startActivity(mapIntent)
        }
        googleMap.uiSettings.apply {
            isZoomControlsEnabled = true
            isZoomGesturesEnabled = false
            isScrollGesturesEnabled = false

        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(candi))
    }
}