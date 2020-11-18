package com.serviceconnect.activity.servicePerson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.serviceconnect.R
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class CurrentServiceActivitySp : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sp_activity_current_service)

        tv_title.text = "Current Service"

    }


}