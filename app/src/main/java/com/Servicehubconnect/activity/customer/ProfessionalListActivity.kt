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
import com.Servicehubconnect.helper.Utils
import com.Servicehubconnect.modal.customer.ProfessionalListDataModel
import com.Servicehubconnect.viewModel.customer.ProfessionalListViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.customer_activity_professional_list.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*
import java.util.*
import kotlin.collections.ArrayList


class ProfessionalListActivity : AppCompatActivity(), View.OnClickListener, ItemListener{
    var viewModel: ProfessionalListViewModel?= null
    var adapter: ProfessionalListAdapter?= null
    var serviceName: String?= null
    var subCategoryId: String?= null
    var latitudeValue: String =""
    var longitudeValue: String =""
    var city: String =""
    var country: String =""
    var keyword: String =""
    var screenType: String =""
    var time_zone: String=""



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
        tv_search_business.setOnClickListener(this)
    }

    private fun initViews() {
        screenType = intent.getStringExtra("SCREEN_TYPE")!!
        serviceName = intent.getStringExtra("serviceName")
        subCategoryId = intent.getStringExtra("subCategoryId")

        if(screenType.equals("SetLocationAddressActivity")){
            latitudeValue = intent.getStringExtra("latitude")!!
            longitudeValue = intent.getStringExtra("longitude")!!
            city = intent.getStringExtra("city")!!
            country = intent.getStringExtra("country")!!
        }


        tv_title.text = serviceName

        val cal = Calendar.getInstance()
         time_zone = cal.timeZone.id.toString()



        rv_professionalList.setHasFixedSize(true)
        rv_professionalList.layoutManager = LinearLayoutManager(this)
    }




    private fun getProfessionalList() {
        keyword = ""
        viewModel!!.getProfessionalList(this, subCategoryId!!, longitudeValue!!, latitudeValue!!, country!!, city!!, keyword!!, time_zone).observe(this, Observer {

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
                else {
                    Utils.showToast(this, getString(R.string.msg_common_error))
                }
            }
        })

    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack ->{
                finish()
            }
            R.id.tv_search_business ->{
                var intent = Intent(this, SearchBusinessActivity::class.java)
                intent.putExtra("serviceName", serviceName)
                intent.putExtra("subCategoryId", subCategoryId)
                intent.putExtra("latitude", latitudeValue)
                intent.putExtra("longitude", longitudeValue)
                intent.putExtra("country", country)
                intent.putExtra("city", city)
                startActivity(intent)
            }

        }
    }

    override fun itemListener(dataModal: Any) {
        var dataItems= dataModal as ProfessionalListDataModel

        var intent = Intent(this, OrderProductsAndServicesActivity::class.java)
        intent.putExtra("professionalId", dataItems.getId())
        intent.putExtra("bussinessId", dataItems.getBussinessId())
        startActivity(intent)
    }


}