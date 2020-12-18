package com.Servicehubconnect.activity.customer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.Servicehubconnect.R
import kotlinx.android.synthetic.main.customer_activity_setting.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class SettingActivity : AppCompatActivity(), View.OnClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_setting)

        initViews()
        setOnClickListener()
    }

    private fun initViews() {
        tv_title.text = this.resources.getString(R.string.txt_settings)
    }

    private fun setOnClickListener() {
        ivBack.setOnClickListener(this)
        tv_profile.setOnClickListener(this)
        tv_change_password.setOnClickListener(this)
        tv_contact_support.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack ->{
                finish()
            }
            R.id.tv_profile ->{
                var intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_change_password ->{
                var intent = Intent(this, ChangePasswordActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_contact_support ->{
                var intent = Intent(this, ContactSupportActivity::class.java)
                startActivity(intent)
            }
        }
    }


}