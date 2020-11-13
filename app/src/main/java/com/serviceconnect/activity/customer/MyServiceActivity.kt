package com.serviceconnect.activity.customer

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.serviceconnect.R
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class MyServiceActivity: AppCompatActivity(){
    var tabs : TabLayout?=null
    var viewPager : ViewPager?= null
    var myServiceViewPager : MyServiceViewPager?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_my_service_activity)
        initViews()
    }


    private fun initViews() {
        tv_title.text = "My Service"
        ivBack.setOnClickListener {
            finish()
        }

        tabs = findViewById<TabLayout>(R.id.activities_tabs)
        viewPager = findViewById<ViewPager>(R.id.vp_my_service)

        tabs!!.addTab(tabs!!.newTab().setText("Upcoming"))
        tabs!!.addTab(tabs!!.newTab().setText("Completed"))
        tabs!!.setTabTextColors(Color.parseColor("#A2A8A2"), resources.getColor(R.color.colorPrimary))
        tabs!!.tabGravity = TabLayout.GRAVITY_FILL

        myServiceViewPager = MyServiceViewPager(supportFragmentManager!!, tabs!!.tabCount)

        viewPager!!.adapter = myServiceViewPager
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        viewPager!!.offscreenPageLimit = 1

        tabs!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager!!.currentItem = tab!!.position
            }
        })
    }




}