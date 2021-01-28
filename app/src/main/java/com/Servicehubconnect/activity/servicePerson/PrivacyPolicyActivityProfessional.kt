package com.Servicehubconnect.activity.servicePerson

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.Servicehubconnect.R
import com.Servicehubconnect.viewModel.PrivacyPolicyViewModel
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_privacy_policy.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class PrivacyPolicyActivityProfessional : AppCompatActivity(){
    var viewModel: PrivacyPolicyViewModel?= null
    var url: String = "http://35.166.234.255/api/user/privacy-policy"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)
         viewModel = ViewModelProviders.of(this).get(PrivacyPolicyViewModel::class.java)
        setOnClickListener()
        loadData()
    }

    private fun loadData() {

        viewModel!!.getPrivacyPolicy(this).observe(this, Observer {

            if(it!= null){

                if(it.has("status") && it.get("status").asString.equals("200")){

                    if(it.has("data") && it.get("data") is JsonObject){
                        var dataObject = it.getAsJsonObject("data")

                        if(dataObject.has("privacy_policy") && !dataObject.get("privacy_policy").isJsonNull){

                            tv_privacy_policy.setText(dataObject.get("privacy_policy").asString)

                        }
                    }
                }
                else{

                }
            }

        })

//        webView_privacy_policy.getSettings().setJavaScriptEnabled(true);
//        webView_privacy_policy.loadUrl(ApiList.PRIVACY_POLICY_URL)
    }

    private fun setOnClickListener() {
        tv_title.setText("Privacy Policy")


        ivBack.setOnClickListener {
            finish()
        }

        tv_OK.setOnClickListener {
            var intent = Intent(this, TermAndConditionActivityProfessional::class.java)
            startActivity(intent)
        }

    }


}