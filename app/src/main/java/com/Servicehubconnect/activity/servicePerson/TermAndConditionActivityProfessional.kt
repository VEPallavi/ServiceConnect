package com.Servicehubconnect.activity.servicePerson

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.Servicehubconnect.R
import com.Servicehubconnect.viewModel.TermAndConditionViewModel
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_terms_condition.*
import kotlinx.android.synthetic.main.activity_terms_condition.tv_OK
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class TermAndConditionActivityProfessional : AppCompatActivity(){
    var url: String =""
    var viewModel: TermAndConditionViewModel?= null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_condition)
        viewModel = ViewModelProviders.of(this).get(TermAndConditionViewModel::class.java)
        setOnClickListener()

        loadData()
    }

    private fun loadData() {


        viewModel!!.getTermCondition(this).observe(this, Observer {

            if(it!= null){

                if(it.has("status") && it.get("status").asString.equals("200")){

                    if(it.has("data") && it.get("data") is JsonObject){
                        var dataObject = it.getAsJsonObject("data")

                        if(dataObject.has("terms_condition") && !dataObject.get("terms_condition").isJsonNull){

                            tv_term_condition.setText(dataObject.get("terms_condition").asString)

                        }
                    }
                }
                else{

                }
            }

        })

//        webview_term_condition.getSettings().setJavaScriptEnabled(true);
//        webview_term_condition.loadUrl(url)
    }

    private fun setOnClickListener() {
        tv_title.setText("Term & Conditions")

        ivBack.setOnClickListener {
            finish()
        }

        tv_OK.setOnClickListener {
            var intent = Intent(this, HomeActivitySP::class.java)
            startActivity(intent)
        }

    }


}
