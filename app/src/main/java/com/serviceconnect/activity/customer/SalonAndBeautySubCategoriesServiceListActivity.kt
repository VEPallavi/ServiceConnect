package com.serviceconnect.activity.customer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.serviceconnect.R
import kotlinx.android.synthetic.main.customer_activity_salon_beauty_subcategories_service_list.*

class SalonAndBeautySubCategoriesServiceListActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_salon_beauty_subcategories_service_list)


        rv_service_ist.setHasFixedSize(true)
        rv_service_ist.layoutManager = LinearLayoutManager(this)

    }


}