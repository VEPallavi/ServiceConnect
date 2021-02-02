package com.Servicehubconnect.activity.customer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.Servicehubconnect.R
import com.Servicehubconnect.adapter.customerApp.ProfessionalListAdapter
import com.Servicehubconnect.callback.ItemListener
import com.Servicehubconnect.viewModel.customer.ProfessionalListViewModel
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.customer_activity_professional_list.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class ProfessionalListActivity : AppCompatActivity(), View.OnClickListener, ItemListener{
    var viewModel: ProfessionalListViewModel?= null
    var adapter: ProfessionalListAdapter?= null
    var serviceName: String?= null
    var subCategoryId: String?= null
    var latitudeValue: String?= null
    var longitudeValue: String?= null
    var city: String =""
    var country: String =""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_professional_list)
        viewModel = ViewModelProviders.of(this).get(ProfessionalListViewModel::class.java)
        initViews()
        getProfessionalList()
        setOnClickListener()
    }


    private fun setOnClickListener() {
        ivBack.setOnClickListener(this)
    }

    private fun initViews() {
        serviceName = intent.getStringExtra("serviceName")
        subCategoryId = intent.getStringExtra("subCategoryId")
        tv_title.text = serviceName

        rv_service_ist.setHasFixedSize(true)
        rv_service_ist.layoutManager = LinearLayoutManager(this)
        adapter = ProfessionalListAdapter(this, this)
        rv_service_ist.adapter = adapter
    }




    private fun getProfessionalList() {
        viewModel!!.getProfessionalList(this, subCategoryId!!, longitudeValue!!, latitudeValue!!, country, city).observe(this, Observer {

            if(it!= null){

                if(it.has("status") && it.get("status").asString.equals("200")){

                    if(it.has("data") && it.get("data") is JsonArray){


                    }
                }
            }
        })

    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack ->{
                finish()
            }
        }
    }

    override fun itemListener(dataModel: Any) {
        var intent = Intent(this, ServiceDetailsAndOrderActivity::class.java)
        startActivity(intent)
    }


}