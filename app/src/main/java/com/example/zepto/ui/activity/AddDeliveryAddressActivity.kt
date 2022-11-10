package com.example.zepto.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.test.AviInterface
import com.example.zepto.constant.constants
import com.example.zepto.databinding.ActivityAddDeliveryAddressBinding
import com.example.zepto.db.RetrofitHelper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.util.*


class AddDeliveryAddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddDeliveryAddressBinding
    private lateinit var addressData: String
    private var choiceKey: Int = 0
    private  var  profileHolderId  :Int= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            com.example.zepto.R.layout.activity_add_delivery_address
        )
        supportActionBar!!.title = "Add Your address"

        //get intent data

        val intentData = intent
        val fullAddress = intentData.getStringExtra("profileEditKey")
        profileHolderId = intentData.getIntExtra("profileHolderIdKey", 3)
        choiceKey = intentData.getIntExtra("choiceKey", 3)
        binding.etHouseNoFirst.setText(fullAddress)
        Log.d("urFullAddress", "onCreate: $fullAddress  $choiceKey $profileHolderId")

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
        if (choiceKey == constants.choiceUpdateProfileFlag) {
            postUpdateStatus()
        } else if (choiceKey == constants.choiceInsertProfileFlag) {
            postSavedAddress()
        }
        Log.d("samos", "fnSubmit:")
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
                        addressData = addressList[0].getAddressLine(0)
                    }
                }
            },
            Looper.myLooper()
        )
    }

    private fun postSavedAddress() {

        val name =
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                binding.etNameProfile.text.toString()
            )
        val phoneNo =
            RequestBody.create("text/plain".toMediaTypeOrNull(), binding.etPhoneNo.text.toString())
        val houseNo = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            binding.etHouseNoFirst.text.toString()
        )







        val owner = RequestBody.create("text/plain".toMediaTypeOrNull(), "avi")

        Log.d(
            "samos", "postSavedAddress: ${binding.etNameProfile.text} " +
                    "${binding.etPhoneNo.text} ${binding.etHouseNoFirst.text} "
        )
        val repo = RetrofitHelper.getClient().create(AviInterface::class.java)

        GlobalScope.launch {
            val call = repo.postUserAddress(name, phoneNo, houseNo, owner)
            if (call.isSuccessful)
                Log.d("samos", "postSavedAddress: Success${call.body()}")
            else
                Log.d("samos", "getSavedAddress: ${call.errorBody()}")
        }
    }

    private fun postUpdateStatus() {
        val name =
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                binding.etNameProfile.text.toString()
            )
        val houseNo = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            binding.etHouseNoFirst.text.toString()
        )
        val id = RequestBody.create("text/plain".toMediaTypeOrNull(), "237")

        Log.d(
            "samos", "postSavedAddress: ${binding.etNameProfile.text} " +
                    "$profileHolderId ${binding.etHouseNoFirst.text} "
        )
        val repo = RetrofitHelper.getClient().create(AviInterface::class.java)

        GlobalScope.launch {
            val call = repo.updateUserAddress(id, name, houseNo)
            if (call.isSuccessful)
                Log.d("samos", "postSavedAddress: Success${call.body()}")
            else
                Log.d("samos", "getSavedAddress: ${call.errorBody()}")
        }
    }
}