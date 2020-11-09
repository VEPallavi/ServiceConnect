package com.serviceconnect.activity.customer

import android.os.Bundle
import android.view.View
import android.widget.HorizontalScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.serviceconnect.R
import com.serviceconnect.adapter.customerApp.TimeListAdapter
import kotlinx.android.synthetic.main.customer_activity_order_place.*


class OrderPlaceActivity : AppCompatActivity(), View.OnClickListener{
    var timeListAdapter : TimeListAdapter?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_order_place)

        rv_time_list.setHasFixedSize(true)
        rv_time_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        timeListAdapter = TimeListAdapter()
        rv_time_list.adapter = timeListAdapter

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack ->{
                finish()
            }
        }
    }


}