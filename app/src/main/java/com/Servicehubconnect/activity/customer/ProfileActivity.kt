package com.Servicehubconnect.activity.customer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.Servicehubconnect.R
import com.Servicehubconnect.helper.AppPreference
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.customer_activity_profile.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class ProfileActivity : AppCompatActivity(), View.OnClickListener{
    var appPreference: AppPreference?= null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_profile)
        appPreference = AppPreference.getInstance(this)

        initViews()
        setOnClickListener()
    }

    private fun initViews() {
        tv_title.text = "Profile"


        tv_name.setText(appPreference!!.getCustomerName())
        tv_phoneNumber.setText(appPreference!!.getCustomerCountryCode()+appPreference!!.getCustomerMobileNo())
        tv_emailId.setText(appPreference!!.getCustomerEmailID())
        if(!appPreference!!.getCustomerProfilePic().equals("") && appPreference!!.getCustomerProfilePic()!= null){
            Glide.with(this)
                    .load(appPreference!!.getCustomerProfilePic())
                    .apply(RequestOptions().placeholder(R.drawable.dummy).error(R.drawable.dummy))
                    .into(civ_profileImage)
        }


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