package com.serviceconnect.activity.customer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.serviceconnect.R
import com.serviceconnect.adapter.customerApp.SalonAndBeautyBusinessListAdapter
import kotlinx.android.synthetic.main.customer_activity_salon_beauty_business_list.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class SalonAndBeautyBusinessListActivity : AppCompatActivity(), View.OnClickListener{
    var adapter: SalonAndBeautyBusinessListAdapter?= null
    var serviceName: String?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_salon_beauty_business_list)

        initViews()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        ivBack.setOnClickListener(this)
    }

    private fun initViews() {
        serviceName = intent.getStringExtra("serviceName")
        tv_title.text = serviceName

        rv_service_ist.setHasFixedSize(true)
        rv_service_ist.layoutManager = LinearLayoutManager(this)
        adapter = SalonAndBeautyBusinessListAdapter(this)
        rv_service_ist.adapter = adapter
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack ->{
                finish()
            }
        }
    }


}