package com.serviceconnect.activity.customer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.serviceconnect.R
import kotlinx.android.synthetic.main.customer_activity_order_service.*


class OrderServiceActivity: AppCompatActivity(), View.OnClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_order_service)

        setOnClickListener()

    }

    private fun setOnClickListener() {
        tv_licence.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_licence ->{
                var intent = Intent(this, LicenceListActivity::class.java)
                startActivity(intent)
            }
        }
    }


}