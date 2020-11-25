package com.Servicehubconnect.activity.customer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.Servicehubconnect.R
import kotlinx.android.synthetic.main.customer_activity_service_detail_and_order.*


class ServiceDetailsAndOrderActivity: AppCompatActivity(), View.OnClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_service_detail_and_order)

        setOnClickListener()

    }

    private fun setOnClickListener() {
        tv_licence.setOnClickListener(this)
        tv_comment.setOnClickListener(this)
        tv_order.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_licence ->{
                var intent = Intent(this, LicenceListActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_comment ->{
                var intent = Intent(this, CommentListActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_order ->{
                var intent = Intent(this, OrderPlaceActivity::class.java)
                startActivity(intent)
            }

        }
    }


}