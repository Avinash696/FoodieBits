package com.example.zepto.ui.activity

import android.Manifest
import android.R
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.zepto.databinding.ActivityAddDeliveryAddressBinding
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import okhttp3.Address
import java.util.*
import kotlin.collections.ArrayList


class AddDeliveryAddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddDeliveryAddressBinding
    private lateinit var addressData: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            com.example.zepto.R.layout.activity_add_delivery_address
        )
        supportActionBar!!.title = "Add delivery address"


        binding.btUseCurrentLocation.setOnClickListener {
            setUpLocationListener()
        }
    }

    fun fnSubmit(view: View) {
        val textInputLayout = binding.textFieldFirst
        textInputLayout.isErrorEnabled = true
        textInputLayout.error = "Please enter a Name"

        val textPhoneFirst = binding.textPhoneFirst
        textPhoneFirst.isErrorEnabled = true
        textPhoneFirst.error = "Please enter a Phone no"

        val textPincodeFirst = binding.textPincodeFirst
        textPincodeFirst.isErrorEnabled = true
        textPincodeFirst.error = "Please enter Pincode"

        val textStateFirst = binding.textStateFirst
        textStateFirst.isErrorEnabled = true
        textStateFirst.error = "Please enter State"

        val textCityFirst = binding.textCityFirst
        textCityFirst.isErrorEnabled = true
        textCityFirst.error = "Please enter City"

        val textHouseNoFirst = binding.textHouseNoFirst
        textHouseNoFirst.isErrorEnabled = true
        textHouseNoFirst.error = "Please enter house No"

        val textRoadNameFirst = binding.textRoadNameFirst
        textRoadNameFirst.isErrorEnabled = true
        textRoadNameFirst.error = "Please enter NearBy"

        // if fields not empty

        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra("ProfileData", binding.etHouseNoFirst.text.toString())
        startActivity(intent)
    }

    private fun setUpLocationListener() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        // for getting the current location update after every 2 seconds with high accuracy
//        val locationRequest = LocationRequest().setInterval(10000).setFastestInterval(10000)
//            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        val locationRequest = LocationRequest().setWaitForAccurateLocation(false)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    for (location in locationResult.locations) {
                        Log.d("mm", "onLocationResult:${location.latitude} ${location.longitude} ")

                        val geoCoder =
                            Geocoder(this@AddDeliveryAddressActivity, Locale.getDefault())
                        val addressList =
                            geoCoder.getFromLocation(location.latitude, location.longitude, 1)
                        //address
                        Log.d("rawat", "onLocationResult: $addressList")
                        binding.etPincodeFirst.setText(addressList[0].postalCode)
                        binding.etStateFirst.setText(addressList[0].subAdminArea)
                        binding.etCityFirst.setText(addressList[0].locality)
                        binding.etHouseNoFirst.setText(addressList[0].getAddressLine(0))
                        //
                        addressData = "${addressList[0].getAddressLine(0)}"
                    }
                }
            },
            Looper.myLooper()
        )
    }
}