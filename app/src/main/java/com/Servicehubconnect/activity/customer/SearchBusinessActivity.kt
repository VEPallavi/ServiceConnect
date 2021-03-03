package com.Servicehubconnect.activity.customer

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.Servicehubconnect.R
import com.Servicehubconnect.adapter.customerApp.SearchBusinessAdapter
import com.Servicehubconnect.helper.Utils
import com.Servicehubconnect.modal.customer.ProfessionalListDataModel
import com.Servicehubconnect.viewModel.customer.SearchBusinessViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.customer_activity_search_business.*
import java.util.*
import kotlin.collections.ArrayList


class SearchBusinessActivity : AppCompatActivity(), View.OnClickListener{
    var adapter: SearchBusinessAdapter?= null
    var viewModel: SearchBusinessViewModel?= null
    var businessSearchDataItems = ArrayList<ProfessionalListDataModel>()

    var serviceName: String?= null
    var subCategoryId: String?= null
    var latitudeValue: String?= null
    var longitudeValue: String?= null
    var city: String?= null
    var country: String?= null
    var timeZone: String =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_search_business)
        viewModel = ViewModelProviders.of(this).get(SearchBusinessViewModel::class.java)

        initViews()
    }


    private fun initViews() {
        serviceName = intent.getStringExtra("serviceName")
        subCategoryId = intent.getStringExtra("subCategoryId")
        latitudeValue = intent.getStringExtra("latitude")
        longitudeValue = intent.getStringExtra("longitude")
        city = intent.getStringExtra("city")
        country = intent.getStringExtra("country")


        val cal = Calendar.getInstance()
        timeZone = cal.timeZone.toString()

        iv_back.setOnClickListener(this)
        iv_cancel.setOnClickListener(this)

        rv_businessList.setHasFixedSize(true)
        rv_businessList.layoutManager = LinearLayoutManager(this)
        adapter = SearchBusinessAdapter(this, businessSearchDataItems!!)
        rv_businessList.adapter = adapter

        et_search.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence, i: Int, i1: Int, i2: Int) {

            }
            override fun onTextChanged(s: CharSequence, i: Int, i1: Int, i2: Int) {
                if (s.toString().trim { it <= ' ' }.length > 2) {
                    rv_businessList.visibility = View.VISIBLE
                    iv_cancel.visibility = View.VISIBLE
                    getSearchDetails(s.toString())
                } else {
                    rv_businessList.visibility = View.GONE
                    iv_cancel.visibility = View.GONE
                    cl_no_data_found.visibility=View.GONE
                }
            }
            override fun afterTextChanged(s: Editable) {

            }
        })
    }




    fun getSearchDetails(searchData: String){

        if(checkValidation()){

            pb_loader.visibility = View.VISIBLE
            rv_businessList.visibility = View.INVISIBLE
            cl_no_data_found.visibility = View.INVISIBLE

            viewModel!!.searchBusiness(this, subCategoryId!!, longitudeValue!!, latitudeValue!!
                    , country!!, city!!, searchData, timeZone).observe(this, Observer {

                if(it!= null){

                    if(it.has("status") && it.get("status").asString.equals("200")){

                        rv_businessList.visibility = View.VISIBLE
                        pb_loader.visibility = View.GONE
                        cl_no_data_found.visibility = View.GONE


                        if(it.has("data") && it.get("data") is JsonArray){
                            val type = object : TypeToken<ArrayList<ProfessionalListDataModel>>() {}.type
                            var dataList = Gson().fromJson<ArrayList<ProfessionalListDataModel>>(it.get("data"), type)

                            if(dataList.size > 0){
                                rv_businessList.visibility = View.VISIBLE
                                cl_no_data_found.visibility = View.GONE
                                adapter = SearchBusinessAdapter(this, dataList)
                                rv_businessList.adapter = adapter
                            }
                            else{
                                rv_businessList.visibility = View.GONE
                                cl_no_data_found.visibility = View.VISIBLE
                            }
                        }
                    }
                    else{
                        pb_loader.visibility = View.GONE
                        cl_no_data_found.visibility = View.VISIBLE
                        rv_businessList.visibility = View.GONE
                    }
                }
            })

        }
    }



    override fun onClick(v: View?) {
        when(v?.id){

            R.id.iv_back->{
                finish()
            }
            R.id.iv_cancel ->{
                et_search.setText("")
            }
        }
    }



    private fun checkValidation(): Boolean {
        if (!Utils.isInternetAvailable(this)) {
            Utils.showToast(this, resources.getString(R.string.msg_no_internet))
            return false
        }
        return true
    }




}