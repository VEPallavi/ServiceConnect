//package com.liquoruser.ui
//
//import android.Manifest
//import android.app.Activity
//import android.content.Context
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.location.Location
//import android.location.LocationManager
//import android.os.Bundle
//import android.os.Looper
//import android.provider.Settings
//import android.text.Editable
//import android.text.TextUtils
//import android.text.TextWatcher
//import android.util.Log
//import android.view.View
//import android.widget.Toast
//import androidx.annotation.Nullable
//import androidx.core.app.ActivityCompat
//import com.google.android.gms.common.api.Status
//import com.google.android.gms.location.*
//import com.google.android.gms.tasks.OnCompleteListener
//import com.google.android.gms.tasks.Task
//import com.google.android.libraries.places.api.Places
//import com.google.android.libraries.places.api.model.Place
//import com.google.android.libraries.places.api.net.PlacesClient
//import com.google.android.libraries.places.widget.Autocomplete
//import com.google.android.libraries.places.widget.AutocompleteActivity
//import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
//import com.liquoruser.R
//import com.liquoruser.ui.base.BaseActivity
//import com.liquoruser.utility.GlobalAccess
//import com.liquoruser.utility.SlideAnimationUtil
//import kotlinx.android.synthetic.main.activity_shopping_address.*
//import java.util.*
//
//
//class ShoppingAddressActivity : BaseActivity(), View.OnClickListener {
//
//    private var mFusedLocationClient: FusedLocationProviderClient? = null
//    private val LOCATION_PERMISSION = 44
//    private val AUTOCOMPLETE_REQUEST_CODE = 41
//    private var isNeedToUpdateAddress: Boolean = true
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_shopping_address)
//
//        initUI()
//        getLastLocation()
//        setupClickListener()
//    }
//
//    private fun initUI() {
//        val apiKey = "AIzaSyAJW2TL6mlGK4xfxpyjFoDW7y6MUxNepuA"
//        if (!Places.isInitialized()) {
//            Places.initialize(getApplicationContext(), apiKey);
//        }
//        // Create a new Places client instance.
//        // Create a new Places client instance.
//        val placesClient: PlacesClient = Places.createClient(this)
//
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        et_address.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//                if (!s?.isEmpty()!!) {
//                    iv_clear.visibility = View.VISIBLE
//                } else {
//                    iv_clear.visibility = View.GONE
//                }
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//        })
//    }
//
//    private fun setupClickListener() {
//        iv_clear.setOnClickListener(this)
//        btn_loginSignup.setOnClickListener(this)
//        btn_continue.setOnClickListener(this)
//    }
//
//    override fun onClick(v: View?) {
//        when (v?.id) {
//            R.id.iv_clear -> {
//                clearAddressTextField()
//                onSearchCalled()
//                isNeedToUpdateAddress = true
//            }
//            R.id.btn_loginSignup -> {
//                launchLoginScreen()
//            }
//            R.id.btn_continue -> {
//                launchHomeScreen()
//            }
//        }
//    }
//
//    private fun clearAddressTextField() {
//        if (!TextUtils.isEmpty(et_address.text.toString().trim())) {
//            et_address.setText("")
//            iv_clear.visibility = View.GONE
//        }
//    }
//
//    private fun launchHomeScreen() {
//        val loginIntent = Intent(this@ShoppingAddressActivity, HomeActivity::class.java)
//        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//        startActivity(loginIntent)
//        finish()
//        SlideAnimationUtil.slideNextAnimation(this@ShoppingAddressActivity)
//    }
//
//    private fun launchLoginScreen() {
//        val loginIntent = Intent(this@ShoppingAddressActivity, LoginActivity::class.java)
//        startActivity(loginIntent)
//        SlideAnimationUtil.slideNextAnimation(this@ShoppingAddressActivity)
//    }
//
//    private fun updateAddressField(latitude: Double, longitude: Double) {
//        if (isNeedToUpdateAddress)
//        {
//            et_address.setText(
//                GlobalAccess.getCompleteAddressFromLatLong(
//                    this@ShoppingAddressActivity,
//                    latitude,
//                    longitude
//                )
//            )
//        }
//    }
//
//
//    private fun getLastLocation() {
//        // check if permissions are given
//        if (checkPermissions()) {
//
//            // check if location is enabled
//            if (isLocationEnabled()) {
//
//                // getting last
//                // location from
//                // FusedLocationClient
//                // object
//                mFusedLocationClient!!.lastLocation
//                    .addOnCompleteListener(object : OnCompleteListener<Location?> {
//                        override fun onComplete(task: Task<Location?>) {
//                            val location: Location? = task.getResult()
//                            if (location == null) {
//                                requestNewLocationData()
//                            } else {
//                                updateAddressField(location.latitude, location.longitude)
//                            }
//                        }
//                    })
//            } else {
//                Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG)
//                    .show()
//                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//                startActivity(intent)
//            }
//        } else {
//            // if permissions aren't available,
//            // request for permissions
//            requestPermissions()
//        }
//    }
//
//    private fun requestNewLocationData() {
//
//        // Initializing LocationRequest
//        // object with appropriate methods
//        val mLocationRequest = LocationRequest()
//        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        mLocationRequest.interval = 5
//        mLocationRequest.fastestInterval = 0
//        mLocationRequest.numUpdates = 1
//
//        // setting LocationRequest
//        // on FusedLocationClient
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//        mFusedLocationClient?.requestLocationUpdates(
//            mLocationRequest,
//            mLocationCallback,
//            Looper.myLooper()
//        )
//    }
//
//    private val mLocationCallback: LocationCallback = object : LocationCallback() {
//        override fun onLocationResult(locationResult: LocationResult) {
//            val mLastLocation = locationResult.lastLocation
//            updateAddressField(mLastLocation.latitude, mLastLocation.longitude)
//        }
//    }
//
//    private fun checkPermissions(): Boolean {
//        return ActivityCompat.checkSelfPermission(
//            this,
//            Manifest.permission.ACCESS_COARSE_LOCATION
//        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//            this,
//            Manifest.permission.ACCESS_FINE_LOCATION
//        ) == PackageManager.PERMISSION_GRANTED
//
//        // If we want background location
//        // on Android 10.0 and higher,
//        // use:
//        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
//    }
//
//    // method to request for permissions
//    private fun requestPermissions() {
//        ActivityCompat.requestPermissions(
//            this, arrayOf(
//                Manifest.permission.ACCESS_COARSE_LOCATION,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ), LOCATION_PERMISSION
//        )
//    }
//
//    // method to check
//    // if location is enabled
//    private fun isLocationEnabled(): Boolean {
//        val locationManager =
//            getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
//            LocationManager.NETWORK_PROVIDER
//        )
//    }
//
//    // If everything is alright then
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String?>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == LOCATION_PERMISSION) {
//            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getLastLocation()
//            }
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        if (checkPermissions()) {
//            getLastLocation()
//        }
//    }
//
//    fun onSearchCalled() {
//        // Set the fields to specify which types of place data to return.
//        val fields: List<Place.Field> = Arrays.asList(
//            Place.Field.ID,
//            Place.Field.NAME,
//            Place.Field.ADDRESS,
//            Place.Field.LAT_LNG
//        )
////        // Start the autocomplete intent.
//        //.setCountry("IN")
//        val intent: Intent = Autocomplete.IntentBuilder(
//            AutocompleteActivityMode.FULLSCREEN, fields
//        )
//            .build(this)
//        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
//    }
//
//    override fun onActivityResult(
//        requestCode: Int,
//        resultCode: Int,
//        @Nullable data: Intent?
//    ) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
//            if (resultCode == Activity.RESULT_OK) {
//                val place: Place = Autocomplete.getPlaceFromIntent(data!!)
//                Log.i(
//                    "FragmentActivity.TAG",
//                    "Place: " + place.getName().toString() + ", " + place.getId()
//                        .toString() + ", " + place.getAddress()
//                )
//                val address: String = place.getAddress()!!
//                val latLng = place.latLng
//                updateAddressField(latLng?.latitude!!, latLng?.longitude!!)
//                isNeedToUpdateAddress = false
//                // do query with address
//            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
//                // TODO: Handle the error.
//                val status: Status = Autocomplete.getStatusFromIntent(data!!)
//                Toast.makeText(
//                    this@ShoppingAddressActivity,
//                    "Error: " + status.getStatusMessage(),
//                    Toast.LENGTH_LONG
//                ).show()
//                isNeedToUpdateAddress = true
//                Log.i("FragmentActivity.TAG", status.getStatusMessage()!!)
//            } else if (resultCode == Activity.RESULT_CANCELED) {
//                // The user canceled the operation.
//                isNeedToUpdateAddress = true
//            }
//        }
//    }
//}