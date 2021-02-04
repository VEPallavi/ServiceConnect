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
import com.Servicehubconnect.modal.customer.ProfessionalListDataModel
import com.Servicehubconnect.viewModel.customer.ProfessionalListViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.customer_activity_professional_list.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class ProfessionalListActivity : AppCompatActivity(), View.OnClickListener, ItemListener{
    var viewModel: ProfessionalListViewModel?= null
    var adapter: ProfessionalListAdapter?= null
    var serviceName: String?= null
    var subCategoryId: String?= null
    var latitudeValue: String?= null
    var longitudeValue: String?= null
    var city: String?= null
    var country: String?= null
    var keyword: String?= null



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
        latitudeValue = intent.getStringExtra("latitude")
        longitudeValue = intent.getStringExtra("longitude")
        city = intent.getStringExtra("city")
        country = intent.getStringExtra("country")

        tv_title.text = serviceName

        rv_professionalList.setHasFixedSize(true)
        rv_professionalList.layoutManager = LinearLayoutManager(this)
    }




    private fun getProfessionalList() {
        keyword = ""
        viewModel!!.getProfessionalList(this, subCategoryId!!, longitudeValue!!, latitudeValue!!, country!!, city!!, keyword!!).observe(this, Observer {

            if(it!= null){

                if(it.has("status") && it.get("status").asString.equals("200")){

                    if(it.has("data") && it.get("data") is JsonArray){
                        val type = object : TypeToken<ArrayList<ProfessionalListDataModel>>() {}.type
                        var professionalList = Gson().fromJson<ArrayList<ProfessionalListDataModel>>(it.get("data"), type)

                        if(professionalList.size > 0){
                            rv_professionalList.visibility = View.VISIBLE
                            tv_noDataFound.visibility = View.GONE
                            adapter = ProfessionalListAdapter(this, professionalList, this)
                            rv_professionalList.adapter = adapter
                        }
                        else{
                            rv_professionalList.visibility = View.GONE
                            tv_noDataFound.visibility = View.VISIBLE
                        }
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