package com.Servicehubconnect.activity.customer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.Servicehubconnect.R
import com.Servicehubconnect.viewModel.customer.SettingTermAndConditionViewModel
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_common.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class SettingTermAndConditionActivity : AppCompatActivity(){
    var viewModel: SettingTermAndConditionViewModel?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        viewModel = ViewModelProviders.of(this).get(SettingTermAndConditionViewModel::class.java)


        initViews()
        hitApiTermAndCondition()

    }

    private fun hitApiTermAndCondition() {
        viewModel!!.getTermAndCondition(this).observe(this, Observer {

            if(it!= null){

                if(it.has("status") && it.get("status").asString.equals("200")){

                    if(it.has("data") && it.get("data") is JsonObject){

                        var dataObj = it.getAsJsonObject("data")

                        if(dataObj.has("terms_condition") && !dataObj.get("terms_condition").isJsonNull){

                            tv_description.setText(dataObj.get("terms_condition").asString)

                        }
                    }
                }
            }

        })

    }


    private fun initViews() {
        tv_title.setText("Term & Condition")

        ivBack.setOnClickListener {
            finish()
        }
    }

}