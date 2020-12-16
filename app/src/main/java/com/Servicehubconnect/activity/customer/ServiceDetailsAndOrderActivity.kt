package com.Servicehubconnect.activity.customer

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.Servicehubconnect.R
import com.Servicehubconnect.adapter.customerApp.ServiceDetailsAndOrderViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import kotlinx.android.synthetic.main.customer_activity_service_detail_and_order.*


class ServiceDetailsAndOrderActivity: AppCompatActivity(), View.OnClickListener{
    var tabs: TabLayout?= null
    var viewPager: ViewPager?= null
    var serviceDetailsAndOrderViewPagerAdapter: ServiceDetailsAndOrderViewPagerAdapter?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_service_detail_and_order)

        initViews()
        setOnClickListener()

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
                startActivity(intent)
            }
            R.id.tv_order ->{
                var intent = Intent(this, OrderPlaceActivity::class.java)
                startActivity(intent)
            }

        }
    }


}