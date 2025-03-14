package com.example.broadcastingandgps

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var myBroadcastReceiver: MyBroadcastReceiver

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Check for location permissions and request if not granted
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            getLocation()
        }

        // Set up a button to send a custom broadcast
        val btnStartBroadcast = findViewById<Button>(R.id.btnStartBroadcast)
        btnStartBroadcast.setOnClickListener {
            // Dynamically register the broadcast receiver
            myBroadcastReceiver = MyBroadcastReceiver()
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
                registerReceiver(myBroadcastReceiver, it)
            }
        }

        val btnStopBroadcast = findViewById<Button>(R.id.btnStopBroadcast)
        btnStopBroadcast.setOnClickListener {
            unregisterReceiver(myBroadcastReceiver)
        }
    }

    // Function to get the last known location
    @SuppressLint("MissingPermission")
    private fun getLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val lat = it.latitude
                val lng = it.longitude
                Toast.makeText(this, "Location: $lat, $lng", Toast.LENGTH_LONG).show()
            } ?: run {
                Toast.makeText(this, "Unable to retrieve location", Toast.LENGTH_LONG).show()
            }
        }
    }

    // Handle the result of the location permission request
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE &&
            grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            getLocation()
        } else {
            Toast.makeText(this, "Location permission denied", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myBroadcastReceiver)
    }
}