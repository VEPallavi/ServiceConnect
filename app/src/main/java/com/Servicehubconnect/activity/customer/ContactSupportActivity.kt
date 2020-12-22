package com.Servicehubconnect.activity.customer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.Servicehubconnect.R
import kotlinx.android.synthetic.main.customer_activity_contact_support.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class ContactSupportActivity: AppCompatActivity(), View.OnClickListener{



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_contact_support)

        tv_title.text = "Contact Support"
        setOnClickListener()

    }

    private fun setOnClickListener() {
        ivBack.setOnClickListener(this)
        tv_send.setOnClickListener(this)
        tv_cancel.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack ->{
                finish()
            }
            R.id.tv_send ->{

            }
            R.id.tv_cancel ->{
                finish()
            }
        }
    }


}