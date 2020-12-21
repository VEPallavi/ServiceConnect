package com.Servicehubconnect.activity.customer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.Servicehubconnect.R
import kotlinx.android.synthetic.main.customer_activity_profile.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class ProfileActivity : AppCompatActivity(), View.OnClickListener{




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_profile)

        initViews()
        setOnClickListener()
    }

    private fun initViews() {
        tv_title.text = "Profile"
    }

    private fun setOnClickListener() {

        ivBack.setOnClickListener(this)
        tv_update_profile.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack ->{
                finish()
            }
            R.id.tv_update_profile ->{
                var intent = Intent(this, EditProfileActivity::class.java)
                startActivity(intent)
            }
        }
    }


}