package com.Servicehubconnect.activity.customer

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.Servicehubconnect.R
import com.Servicehubconnect.adapter.customerApp.ServiceDetailsAndOrderViewPagerAdapter
import com.Servicehubconnect.viewModel.customer.ProfessionalDetailsWithProductsAndServicesViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.customer_activity_service_detail_and_order.*


class ProfessionalDetailsWithProductsAndServicesActivity: AppCompatActivity(), View.OnClickListener{
    var viewModel: ProfessionalDetailsWithProductsAndServicesViewModel?= null
    var tabs: TabLayout?= null
    var viewPager: ViewPager?= null
    var serviceDetailsAndOrderViewPagerAdapter: ServiceDetailsAndOrderViewPagerAdapter?= null
    var professionalId: String?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_service_detail_and_order)
        viewModel = ViewModelProviders.of(this).get(ProfessionalDetailsWithProductsAndServicesViewModel::class.java)

        professionalId = intent.getStringExtra("professionalId")

        initViews()
        setOnClickListener()
        getProfessionalDetailsData()

    }

    private fun getProfessionalDetailsData() {
        viewModel!!.getProfessionalDetails(this, professionalId!!).observe(this, Observer {

            if(it!= null){

                if(it.has("status") && it.get("status").asString.equals("200")){

                    if(it.has("data") && it.get("data") is JsonObject){
                        var dataObj = it.getAsJsonObject("data")

                        if(dataObj.has("name") && !dataObj.get("name").isJsonNull){
                            tv_professionName.setText(dataObj.get("name").asString)
                        }

                        if(dataObj.has("bussinessName") && !dataObj.get("bussinessName").isJsonNull){
                            tv_businessName.setText(dataObj.get("bussinessName").asString)
                        }

                        if(dataObj.has("ratingAverage") && !dataObj.get("ratingAverage").isJsonNull){

                            tv_ratingValue.setText(""+dataObj.get("ratingAverage").asInt)

                        }

                        if(dataObj.has("totalRating") && !dataObj.get("totalRating").isJsonNull){
                            tv_ratingCount.setText("("+dataObj.get("totalRating").asInt + "Ratings)")
                        }

                        if(dataObj.has("totalComment") && !dataObj.get("totalComment").isJsonNull){
                            tv_commentsCount.setText(""+dataObj.get("totalComment").asInt + "Comments")
                        }



                        if(dataObj.has("bussiness_info") && dataObj.get("bussiness_info") is JsonObject){
                            var businessObj = dataObj.getAsJsonObject("bussiness_info")

                            if(businessObj.has("open_time") && businessObj.has("close_time")){

                                tv_open_and_close_time.setText(businessObj.get("open_time").asString
                                        + businessObj.get("close_time").asString)

                            }

                        }


                        if(dataObj.has("description") && !dataObj.get("description").isJsonNull){
                            tv_description.setText(dataObj.get("description").asString)
                        }



                    }

                }

            }
        })

    }

    private fun initViews() {
        tabs = findViewById(R.id.activities_tabs)
        viewPager = findViewById(R.id.viewPager)

        for (k in 0..9) {
            tabs!!.addTab(tabs!!.newTab().setText("" + k +"A"))
        }
        tabs!!.setTabTextColors(Color.parseColor("#898989"), Color.parseColor("#FF4081"))

        serviceDetailsAndOrderViewPagerAdapter = ServiceDetailsAndOrderViewPagerAdapter(supportFragmentManager, tabs!!.getTabCount())
        viewPager!!.adapter = serviceDetailsAndOrderViewPagerAdapter
        viewPager!!.setOffscreenPageLimit(1)
        viewPager!!.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabs))


        if (tabs!!.getTabCount() == 2) {
            tabs!!.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabs!!.setTabMode(TabLayout.MODE_SCROLLABLE);
        }

    }

    private fun setOnClickListener() {
        tv_licence.setOnClickListener(this)
        tv_comment.setOnClickListener(this)
        tv_order.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_licence ->{
                var intent = Intent(this, LicenceListActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_comment ->{
                var intent = Intent(this, CommentListActivity::class.java)
                intent.putExtra("professionalId", professionalId)
                startActivity(intent)
            }
            R.id.tv_order ->{
                var intent = Intent(this, OrderPlaceActivity::class.java)
                startActivity(intent)
            }

        }
    }


}