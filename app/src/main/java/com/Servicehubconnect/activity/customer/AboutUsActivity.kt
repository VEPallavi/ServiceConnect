package com.Servicehubconnect.activity.customer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.Servicehubconnect.R
import com.Servicehubconnect.viewModel.customer.AboutUsViewModel

import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_common.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class AboutUsActivity : AppCompatActivity(){
    var viewModel: AboutUsViewModel?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        viewModel = ViewModelProviders.of(this).get(AboutUsViewModel::class.java)


        initViews()
        hitApiAboutUs()
    }

    private fun initViews() {
        tv_title.setText("About Us")

        ivBack.setOnClickListener {
            finish()
        }
    }


    private fun hitApiAboutUs() {
        viewModel!!.aboutUs(this).observe(this, Observer {

            if(it!= null){

                if(it.has("status") && it.get("status").asString.equals("200")){

                    if(it.has("data") && it.get("data") is JsonObject){

                        var dataObj = it.getAsJsonObject("data")

                        if(dataObj.has("about_us") && !dataObj.get("about_us").isJsonNull){

                            tv_description.setText(dataObj.get("about_us").asString)

                        }
                    }
                }
            }
        })
    }


}