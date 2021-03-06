package com.Servicehubconnect.activity.customer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.Servicehubconnect.R
import com.Servicehubconnect.fragment.customerApp.DashboardFragmentCustomer
import com.Servicehubconnect.fragment.customerApp.NotificationFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.customer_app_bar_main.*
import java.util.*

class HomeActivityCustomer : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{
    private var mContext: Context? = null
    private var toolbar: Toolbar? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var mActionBarDrawerToggle: ActionBarDrawerToggle? = null
    private var mManager: FragmentManager? = null
    private var mTransaction: FragmentTransaction? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_home)

        initializeView()
        setUpDrawerLayout()
        setUpClickListener()

    }



    private fun initializeView() {
        mContext = this@HomeActivityCustomer
        toolbar = findViewById(R.id.toolbar_main) as Toolbar

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun setUpDrawerLayout() {
        mDrawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
        mActionBarDrawerToggle = object : ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            override fun onDrawerClosed(drawerView: View) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView)
            }

            override fun onDrawerOpened(drawerView: View) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView)
            }
        }

        mDrawerLayout!!.setDrawerListener(mActionBarDrawerToggle)
    }


    override fun onResume() {
        super.onResume()
        setDisplayFragment(0)
    }

    private fun setUpClickListener() {
        iv_toolbarMenu!!.setOnClickListener(this)
        iv_toolbarBack!!.setOnClickListener(this)
        iv_notification!!.setOnClickListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_track_service_man -> setDisplayFragment(1)
            R.id.nav_my_service -> setDisplayFragment(2)
            R.id.nav_my_invoice -> setDisplayFragment(3)
            R.id.nav_my_rating_comment -> setDisplayFragment(4)
            R.id.nav_reschedule_service -> setDisplayFragment(5)
            R.id.nav_refer_friends -> setDisplayFragment(6)
            R.id.nav_notification -> setDisplayFragment(7)
            R.id.nav_support -> setDisplayFragment(8)
            R.id.nav_settings -> setDisplayFragment(9)
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_toolbarMenu ->
            {
                openOrCloseDrawerLayout()
            }
            R.id.iv_toolbarBack ->
            {
                handleBackPress()
            }
            R.id.iv_notification ->
            {
                setDisplayFragment(7)
            }

        }
    }

    fun setDisplayFragment(id: Int) {
        var mFragment: Fragment? = null
        when (id) {
            0 ->{
                mFragment = DashboardFragmentCustomer()
                replaceFragment(mFragment)
            }
            1 -> {
                var intent = Intent(this, TrackServiceManActivity::class.java)
                startActivity(intent)
            }
            2 -> {
                var intent = Intent(this, MyServiceActivity::class.java)
                startActivity(intent)
            }
            3 -> {
                var intent = Intent(this, MyInvoiceActivity::class.java)
                startActivity(intent)
            }
            4 -> {
                var intent = Intent(this, MyRatingAndCommentActivity::class.java)
                startActivity(intent)
            }
            5 -> {
                var intent = Intent(this, RescheduledServiceActivity::class.java)
                startActivity(intent)
            }
            6 -> {
                var intent = Intent(this, ReferFriendsActivity::class.java)
                startActivity(intent)
            }
            7 -> {
//                mFragment = NotificationFragment()
//                replaceFragment(mFragment)
            }
            8 -> {
                var intent = Intent(this, SupportActivity::class.java)
                startActivity(intent)
            }
            9-> {
               var intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
            }
        }
    }




    fun handleBackPress() {
        if (mManager!!.findFragmentById(R.id.container) is DashboardFragmentCustomer)
        {
            finish()
        }
        else if (mManager!!.findFragmentById(R.id.container) is NotificationFragment)
        {
            mManager!!.popBackStackImmediate()
        }
        else
        {
            mManager!!.popBackStackImmediate()
        }
    }


    override fun onBackPressed() {
        if (mDrawerLayout!!.isDrawerOpen(GravityCompat.START))
        {
            mDrawerLayout!!.closeDrawer(GravityCompat.START)
        } else
        {
            handleBackPress()
        }
    }

    fun setToolbarTitle(title: String)
    {
        toolbarTitle!!.text = title
    }

    fun setToolbarMenuVisibility(isVisible: Boolean) {
        if (isVisible) {
            iv_toolbarMenu!!.setVisibility(View.VISIBLE)
        } else {
            iv_toolbarMenu!!.setVisibility(View.GONE)
        }
    }

    fun setToolbarBackVisibility(isVisible: Boolean) {
        if (isVisible) {
            iv_toolbarBack!!.setVisibility(View.VISIBLE)
        } else {
            iv_toolbarBack!!.setVisibility(View.GONE)
        }
    }

    fun setToolbarNotificationVisibility(isVisible: Boolean) {
        if (isVisible) {
            iv_notification!!.setVisibility(View.VISIBLE)
        } else {
            iv_notification!!.setVisibility(View.GONE)
        }
    }




    private fun openOrCloseDrawerLayout() {
        if (mDrawerLayout!!.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout!!.closeDrawers()
        } else {
            mDrawerLayout!!.openDrawer(Gravity.LEFT)
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        mManager = supportFragmentManager
        mTransaction = mManager!!.beginTransaction()
        mTransaction!!.replace(R.id.container, fragment)
        mTransaction!!.addToBackStack(null)
        mTransaction!!.commit()
    }


}