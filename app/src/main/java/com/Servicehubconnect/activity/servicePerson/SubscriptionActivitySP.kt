package com.Servicehubconnect.activity.servicePerson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.Servicehubconnect.R
import kotlinx.android.synthetic.main.sp_activity_subscription.*

class SubscriptionActivitySP : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sp_activity_subscription)


        initViews()

    }

    private fun initViews() {
        rv_subscription.setHasFixedSize(true)
        rv_subscription.layoutManager = LinearLayoutManager(this)


    }


}