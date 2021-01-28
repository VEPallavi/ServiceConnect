package com.Servicehubconnect.activity.customer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.Servicehubconnect.R
import com.Servicehubconnect.viewModel.PrivacyPolicyViewModel
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_privacy_policy.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class PrivacyPolicyActivityCustomer : AppCompatActivity(), View.OnClickListener{
    var viewModel: PrivacyPolicyViewModel?= null



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

        tv_OK.setOnClickListener(this)

        ivBack.setOnClickListener {
            finish()
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_OK ->{
                hitApi()
            }
        }
    }

    private fun hitApi() {
        viewModel!!.submitPrivacyPolicy(this).observe(this, Observer {

            if(it!= null && it.has("status") && it.get("status").asString.equals("200")){
                var intent = Intent(this, TermAndConditionActivityCustomer::class.java)
                startActivity(intent)
            }
        })
    }


}