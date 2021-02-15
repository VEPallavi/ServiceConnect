package com.Servicehubconnect.activity.customer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.Servicehubconnect.R
import com.Servicehubconnect.activity.LoginActivity
import com.Servicehubconnect.activity.WebViewActivity
import com.Servicehubconnect.helper.AppPreference
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
        tv_sign_out.setOnClickListener(this)
        tv_profile.setOnClickListener(this)
        tv_share.setOnClickListener(this)
        tv_change_password.setOnClickListener(this)
        tv_contact_support.setOnClickListener(this)
        tv_aboutUs.setOnClickListener(this)
        tv_privacy_policy.setOnClickListener(this)
        tv_term_condition.setOnClickListener(this)
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
            R.id.tv_share ->{
            //    shareLink()
            }
            R.id.tv_change_password ->{
//                var intent = Intent(this, ChangePasswordActivity::class.java)
//                startActivity(intent)
            }
            R.id.tv_contact_support ->{
//                var intent = Intent(this, ContactSupportActivity::class.java)
//                startActivity(intent)
            }
            R.id.tv_aboutUs ->{
//                var intent = Intent(this, WebViewActivity::class.java)
//                intent.putExtra("screenType", "About Us")
//                intent.putExtra("url","https://www.google.com/")
//                startActivity(intent)
            }
            R.id.tv_privacy_policy ->{
//                var intent = Intent(this, WebViewActivity::class.java)
//                intent.putExtra("screenType", "Privacy Policy")
//                intent.putExtra("url","https://www.google.com/")
//                startActivity(intent)
            }
            R.id.tv_term_condition ->{
//                var intent = Intent(this, WebViewActivity::class.java)
//                intent.putExtra("screenType", "Term & Condition")
//                intent.putExtra("url","https://www.google.com/")
//                startActivity(intent)
            }

            R.id.tv_sign_out ->{

                AppPreference.getInstance(this).setAppType("")
                AppPreference.getInstance(this).setAuthToken("")
                AppPreference.getInstance(this).setCustomerUserID("")
                AppPreference.getInstance(this).setCustomerName("")

                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }

        }
    }

    private fun shareLink() {
        var urlToShare = this.resources.getString(R.string.share_text) + this.packageName
        var intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, urlToShare)
        startActivity(intent)
    }


}