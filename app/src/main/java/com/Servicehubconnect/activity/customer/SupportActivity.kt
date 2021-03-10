package com.Servicehubconnect.activity.customer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.Servicehubconnect.R
import com.Servicehubconnect.viewModel.customer.SupportViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.customer_activity_support.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class SupportActivity : AppCompatActivity(), View.OnClickListener, OnMapReadyCallback {
    var viewModel: SupportViewModel?= null
    var mMap: GoogleMap?= null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_support)
        viewModel = ViewModelProviders.of(this).get(SupportViewModel::class.java)

        tv_title.text = resources.getString(R.string.txt_support)
        ivBack.setOnClickListener(this)

        setDataOnMap()
        getData()
    }

    private fun getData() {
        viewModel!!.getSupportData(this).observe(this, Observer {

            if(it!= null){

                if(it.has("status") && it.get("status").asString.equals("200")){

                    if(it.has("data") && it.get("data") is JsonObject){
                        var dataObj =  it.getAsJsonObject("data")

                        if(dataObj.has("address") && !dataObj.get("address").isJsonNull){
                            tv_address.setText(dataObj.get("address").asString)

                        }

                        if(dataObj.has("latitude") && dataObj.has("longitude")){

                            if(!dataObj.get("latitude").isJsonNull && !dataObj.get("longitude").isJsonNull)

                            setLatLng(dataObj.get("latitude").asDouble ,dataObj.get("longitude").asDouble)

                        }


                        if(dataObj.has("email") && !dataObj.get("email").isJsonNull){
                            tv_emailId.setText(dataObj.get("email").asString)

                        }

                        if(dataObj.has("contact_Number") && !dataObj.get("contact_Number").isJsonNull){
                            tv_phoneNumber.setText(dataObj.get("contact_Number").asString)

                            if(dataObj.has("country_code") && !dataObj.get("country_code").isJsonNull){

                                tv_phoneNumber.setText(dataObj.get("country_code").asString + " " +dataObj.get("contact_Number").asString)
                            }

                        }

                    }

                }


            }
        })
    }

    private fun setDataOnMap() {
        val supportMapFragment = (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)!!
        supportMapFragment.getMapAsync(this@SupportActivity)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack ->{
                finish()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap

    }

    private fun setLatLng(latitude: Double, longitude: Double) {
        val location = LatLng(latitude, longitude)
        mMap!!.addMarker(MarkerOptions().position(location).title("Marker in Your Location"))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }


}