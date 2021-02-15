package com.Servicehubconnect.activity.customer

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import com.Servicehubconnect.R
import com.Servicehubconnect.helper.Utils
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import kotlinx.android.synthetic.main.customer_activity_set_location.*
import java.io.IOException
import java.util.*


class SetLocationAddressActivity : AppCompatActivity(), View.OnClickListener, OnMapReadyCallback{
    var mGoogleMap: GoogleMap? = null
    var cv_currentLocation: CardView?= null
    var ed_currentLocation: EditText?= null
    var iv_cancel: ImageView ?= null
    var iv_fetchLocation: ImageView?= null
    var tv_selectLocation: TextView?= null
    var ivBack: ImageView?= null
    var tv_title: TextView?= null
    var currentLocation: Location?= null
   // var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private val REQUEST_CODE = 101
    var geocoder: Geocoder? = null
    var addresses: List<Address>? = null
    var addMoreDetailsDialog: Dialog? = null
    var addOtherLocationDialog: Dialog? = null
    var currentLocationAddress: String? = null
    var destinationAddress: String?= null
    var serviceName: String? = null
    var subCategoryId: String?= null
    var isRequiredTwoLocation: Boolean?= null

    private val AUTOCOMPLETE_REQUEST_CODE_YOUR_LOCATION = 41
    private val AUTOCOMPLETE_REQUEST_CODE_DESTINATION = 42
    private var isNeedToUpdateAddress: Boolean = true

    var latitudeValue: String?= null
    var longitudeValue: String?= null
    var city: String =""
    var state: String =""
    var country: String =""
    private val LOCATION_PERMISSION = 44
    private var mFusedLocationClient: FusedLocationProviderClient? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_set_location)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        serviceName = intent.getStringExtra("serviceName")
        subCategoryId = intent.getStringExtra("subCategoryId")
        isRequiredTwoLocation = intent.getBooleanExtra("is_required_two_location", false)


        if(isRequiredTwoLocation == true){

        }
        else{
            divider_1.visibility = View.GONE
            ed_destination.visibility = View.GONE
        }

        val supportMapFragment = (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)!!
        supportMapFragment.getMapAsync(this@SetLocationAddressActivity)

      //  fetchLocation()
        getLastLocation()
        initViews()
    }

    private fun initViews() {
        val apiKey = resources.getString(R.string.map_key)//"AIzaSyAJW2TL6mlGK4xfxpyjFoDW7y6MUxNepuA"
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }

        ivBack = findViewById(R.id.ivBack)
        tv_title = findViewById(R.id.tv_title)
        ed_currentLocation = findViewById(R.id.ed_currentLocation)
        cv_currentLocation = findViewById(R.id.cv_currentLocation)
        iv_cancel = findViewById(R.id.iv_cancel)
        iv_fetchLocation = findViewById(R.id.iv_fetchLocation)
        tv_selectLocation = findViewById(R.id.tv_selectLocation)

        tv_title!!.setText("Set Location")
        ivBack!!.setOnClickListener(this)
        iv_fetchLocation!!.setOnClickListener(this)
        tv_selectLocation!!.setOnClickListener(this)
        iv_cancel!!.setOnClickListener(this)
        ed_destination.setOnClickListener(this)
    }

//    private fun fetchLocation() {
//        if (ActivityCompat.checkSelfPermission(
//                        this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                        this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
//            return
//        }
//        val task = mFusedLocationClient!!.lastLocation
//        task.addOnSuccessListener { location ->
//            if (location != null) {
//                currentLocation = location
//                updateLocation(location.latitude, location.longitude)
//                updateMapLocation(location.latitude, location.longitude)
//            }
//        }
//    }



    private fun getLastLocation() {
// check if permissions are given
        if (checkPermissions()) {

// check if location is enabled
            if (isLocationEnabled()) {

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                mFusedLocationClient!!.lastLocation
                        .addOnCompleteListener(object : OnCompleteListener<Location?> {
                            override fun onComplete(task: Task<Location?>) {
                                val location: Location? = task.getResult()
                                if (location == null) {
                                    requestNewLocationData()
                                } else {
                                    updateLocation(location.latitude, location.longitude)
                                    updateMapLocation(location.latitude, location.longitude)
                                }
                            }
                        })
            } else {
                Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG)
                        .show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
// if permissions aren't available,
// request for permissions
            requestPermissions()
        }
    }



    private fun requestNewLocationData() {

// Initializing LocationRequest
// object with appropriate methods
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 5
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mFusedLocationClient?.requestLocationUpdates(
                mLocationRequest,
                mLocationCallback,
                Looper.myLooper()
        )
    }

    private val mLocationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation = locationResult.lastLocation
            updateLocation(mLastLocation.latitude, mLastLocation.longitude)
            updateMapLocation(mLastLocation.latitude, mLastLocation.longitude)

           // updateAddressField(mLastLocation.latitude, mLastLocation.longitude)
        }
    }


    override fun onResume() {
        super.onResume()
        if (checkPermissions()) {
            getLastLocation()
        }
    }

    private fun checkPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

// If we want background location
// on Android 10.0 and higher,
// use:
// ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // method to request for permissions
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
                this, arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        ), LOCATION_PERMISSION
        )
    }

    // method to check
// if location is enabled
    private fun isLocationEnabled(): Boolean {
        val locationManager =
                getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        )
    }




    private fun updateLocation(latitude: Double, longitude: Double) {
        geocoder = Geocoder(this@SetLocationAddressActivity, Locale.getDefault())
        try {
            addresses = geocoder!!.getFromLocation(latitude, longitude, 1)
            currentLocationAddress = addresses!!.get(0).getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            city = addresses!!.get(0).locality
            state = addresses!!.get(0).adminArea
            country = addresses!!.get(0).countryName
            val postalCode = addresses!!.get(0).postalCode
            val knownName = addresses!!.get(0).featureName
            Log.e("<<TAG>>", "address index=" + currentLocationAddress)
            Log.e("<<TAG>>", "city index=" + city)
            Log.e("<<TAG>>", "state index=" + state)
            Log.e("<<TAG>>", "country index=" + country)
            Log.e("<<TAG>>", "postalCode index=" + postalCode)
            Log.e("<<TAG>>", "knownName index=" + knownName)
            latitudeValue = latitude.toString()
            longitudeValue = longitude.toString()
            ed_currentLocation!!.setText(currentLocationAddress)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        updateMapLocation(latitude, longitude)
    }


    override fun onMapReady(googleMap: GoogleMap?) {
        mGoogleMap = googleMap
        //updateMapLocation(currentLocation!!.getLatitude(), currentLocation!!.getLongitude())
    }

    private fun updateMapLocation(latitude: Double, longitude: Double)
    {
        val latLng = LatLng(latitude, longitude)
        val markerOptions = MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_point_icon))
                .title("I am here!")
        mGoogleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        mGoogleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20f))
        mGoogleMap?.addMarker(markerOptions)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
       if(requestCode == REQUEST_CODE){
           if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
              // fetchLocation()
               getLastLocation()
           }
       }
    }


    private fun openAddMoreDetailsDialog() {
        addMoreDetailsDialog = Dialog(this)
        addMoreDetailsDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        addMoreDetailsDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        addMoreDetailsDialog!!.setContentView(R.layout.customer_dialog_add_more_details)
        addMoreDetailsDialog!!.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        addMoreDetailsDialog!!.setCanceledOnTouchOutside(false)
        addMoreDetailsDialog!!.show()
        val tv_done = addMoreDetailsDialog!!.findViewById<TextView>(R.id.tv_done)
        val tv_address = addMoreDetailsDialog!!.findViewById<TextView>(R.id.tv_address)
        val tv_address_with_suite_appartment = addMoreDetailsDialog!!.findViewById<EditText>(R.id.tv_address_with_suite_appartment)
        tv_address.text = currentLocationAddress
        tv_done.setOnClickListener {
            addMoreDetailsDialog!!.dismiss()
            val intent = Intent(this@SetLocationAddressActivity, ProfessionalListActivity::class.java)
            intent.putExtra("serviceName", serviceName)
            intent.putExtra("subCategoryId", subCategoryId)
            intent.putExtra("latitude", latitudeValue)
            intent.putExtra("longitude", longitudeValue)
            intent.putExtra("country", country)
            intent.putExtra("city", city)
            startActivity(intent)





        /*    var intent = Intent(this, ProfessionalDetailsWithProductsAndServicesActivity::class.java)
            intent.putExtra("professionalId", "12")
            startActivity(intent)*/


          //  openDialogOtherLocation()
        }
    }


    private fun openDialogOtherLocation() {
        addOtherLocationDialog = Dialog(this)
        addOtherLocationDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        addOtherLocationDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        addOtherLocationDialog!!.setContentView(R.layout.customer_dialog_other_location)
        addOtherLocationDialog!!.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        addOtherLocationDialog!!.setCanceledOnTouchOutside(false)
        addOtherLocationDialog!!.show()
        val tv_no = addOtherLocationDialog!!.findViewById<Button>(R.id.tv_no)
        val tv_yes = addOtherLocationDialog!!.findViewById<Button>(R.id.tv_yes)
        val tv_other_address = addOtherLocationDialog!!.findViewById<TextView>(R.id.tv_other_address)
        val tv_done = addOtherLocationDialog!!.findViewById<Button>(R.id.tv_done)
        tv_no.setOnClickListener {
            addOtherLocationDialog!!.dismiss()
            //val intent = Intent(this@SetLocationAddressActivity, ProfessionalListActivity::class.java)
           // intent.putExtra("serviceName", serviceName)
            startActivity(intent)
        }
        tv_yes.setOnClickListener {
            tv_yes.visibility = View.GONE
            tv_no.visibility = View.GONE
            tv_other_address.visibility = View.VISIBLE
            tv_done.visibility = View.VISIBLE
        }
        tv_done.setOnClickListener {
            addOtherLocationDialog!!.dismiss()
          //  val intent = Intent(this@SetLocationAddressActivity, ProfessionalListActivity::class.java)
          //  intent.putExtra("serviceName", serviceName)
            startActivity(intent)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack ->{
                finish()
            }
            R.id.iv_fetchLocation ->{
               // fetchLocation()
                getLastLocation()
            }
            R.id.tv_selectLocation ->{
                openAddMoreDetailsDialog()
            }
            R.id.ed_currentLocation ->{

            }
            R.id.ed_destination ->{

                onSearchCalledDestination()
            }

            R.id.iv_cancel ->{
                if (currentLocationAddress != "") {
                    ed_currentLocation!!.setText("")
                    onSearchCalled()
                }
            }
        }
    }

    private fun onSearchCalledDestination() {
        // Set the fields to specify which types of place data to return.
        val fields: List<Place.Field> = Arrays.asList(
                Place.Field.ID,
                Place.Field.NAME,
                Place.Field.ADDRESS,
                Place.Field.LAT_LNG
        )
//        // Start the autocomplete intent.
        //.setCountry("IN")
        val intent: Intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(this)
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE_DESTINATION)
    }

    fun onSearchCalled() {
        // Set the fields to specify which types of place data to return.
        val fields: List<Place.Field> = Arrays.asList(
                Place.Field.ID,
                Place.Field.NAME,
                Place.Field.ADDRESS,
                Place.Field.LAT_LNG
        )
//        // Start the autocomplete intent.
        //.setCountry("IN")
        val intent: Intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(this)
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE_YOUR_LOCATION)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE_YOUR_LOCATION) {
            if (resultCode == Activity.RESULT_OK) {
                val place: Place = Autocomplete.getPlaceFromIntent(data!!)
                val latLng = place.latLng
                updateLocation(latLng?.latitude!!, latLng?.longitude!!)
                // do query with address
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                val status: Status = Autocomplete.getStatusFromIntent(data!!)
                Utils.showToast(this@SetLocationAddressActivity, "Error: " + status.getStatusMessage())
                Log.i("FragmentActivity.TAG", status.getStatusMessage()!!)
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }

         if(requestCode == AUTOCOMPLETE_REQUEST_CODE_DESTINATION){
            if (resultCode == Activity.RESULT_OK) {
                val place: Place = Autocomplete.getPlaceFromIntent(data!!)
                val latLng = place.latLng
                updateLocationForDestination(latLng?.latitude!!, latLng?.longitude!!)
                // do query with address
            }
            else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                val status: Status = Autocomplete.getStatusFromIntent(data!!)
                Utils.showToast(this@SetLocationAddressActivity, "Error: " + status.getStatusMessage())
                Log.i("FragmentActivity.TAG", status.getStatusMessage()!!)
            }
            else if (resultCode == Activity.RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    private fun updateLocationForDestination(latitude: Double, longitude: Double) {

        geocoder = Geocoder(this@SetLocationAddressActivity, Locale.getDefault())
        try {
            addresses = geocoder!!.getFromLocation(latitude, longitude, 1)
            destinationAddress = addresses!!.get(0).getAddressLine(0)
            val city = addresses!!.get(0).locality
            val state = addresses!!.get(0).adminArea
            val country = addresses!!.get(0).countryName
            val postalCode = addresses!!.get(0).postalCode
            val knownName = addresses!!.get(0).featureName
            Log.e("<<TAG>>", "address index=" + destinationAddress)
            Log.e("<<TAG>>", "city index=" + city)
            Log.e("<<TAG>>", "state index=" + state)
            Log.e("<<TAG>>", "country index=" + country)
            Log.e("<<TAG>>", "postalCode index=" + postalCode)
            Log.e("<<TAG>>", "knownName index=" + knownName)
            ed_destination.setText("")
            ed_destination!!.setText(destinationAddress)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


}