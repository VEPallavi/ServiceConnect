package com.Servicehubconnect.activity.customer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.Servicehubconnect.R
import com.Servicehubconnect.adapter.customerApp.SalonAndBeautyBusinessListAdapter
import com.Servicehubconnect.callback.ItemListener
import kotlinx.android.synthetic.main.customer_activity_salon_beauty_business_list.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class SalonAndBeautyBusinessListActivity : AppCompatActivity(), View.OnClickListener, ItemListener{
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
        adapter = SalonAndBeautyBusinessListAdapter(this, this)
        rv_service_ist.adapter = adapter
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