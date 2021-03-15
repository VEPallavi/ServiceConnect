package com.Servicehubconnect.activity.customer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.Servicehubconnect.R
import com.Servicehubconnect.viewModel.customer.SettingPrivacyPolicyViewModel
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_common.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*

class SettingPrivacyPolicyActivity : AppCompatActivity(){
    var viewModel: SettingPrivacyPolicyViewModel?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        viewModel = ViewModelProviders.of(this).get(SettingPrivacyPolicyViewModel::class.java)

        initViews()
        hitApiPrivacyPolicy()

    }

    private fun hitApiPrivacyPolicy() {
        viewModel!!.privacyPolicy(this).observe(this, Observer {


            if(it!= null){

                if(it.has("status") && it.get("status").asString.equals("200")){

                    if(it.has("data") && it.get("data") is JsonObject){

                        var dataObj = it.getAsJsonObject("data")

                        if(dataObj.has("privacy_policy") && !dataObj.get("privacy_policy").isJsonNull){

                            tv_description.setText(dataObj.get("privacy_policy").asString)

                        }
                    }
                }
            }
        })
    }


    private fun initViews() {
        tv_title.setText("Privacy Policy")

        ivBack.setOnClickListener {
            finish()
        }
    }


}