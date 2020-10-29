package com.serviceconnect.fragment.customerApp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.serviceconnect.R
import com.serviceconnect.activity.customer.Automative_AutoRepairActivity
import com.serviceconnect.activity.customer.HomeActivity
import com.serviceconnect.activity.customer.SalonAndBeautyActivity
import com.serviceconnect.helper.customer.ConstantFragmentName
import kotlinx.android.synthetic.main.customer_fragment_dashboard.*


class DashboardFragment : Fragment(), View.OnClickListener{


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupClickListener()
    }

    private fun setupClickListener() {
        cl_salon_beauty.setOnClickListener(this)
        cl_automative_autorepair.setOnClickListener(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.customer_fragment_dashboard, container, false)

        updateToolbar()
        return view
    }

    private fun updateToolbar() {
        (activity as HomeActivity).setToolbarTitle(ConstantFragmentName.DASHBOARD_FRAGMENT)
        (activity as HomeActivity).setToolbarMenuVisibility(true)
        (activity as HomeActivity).setToolbarBackVisibility(false)
        (activity as HomeActivity).setToolbarNotificationVisibility(true)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.cl_salon_beauty ->{
                var intent = Intent(activity!!, SalonAndBeautyActivity::class.java)
                startActivity(intent)
            }
            R.id.cl_automative_autorepair ->{
                var intent = Intent(activity!!, Automative_AutoRepairActivity::class.java)
                startActivity(intent)
            }
        }
    }

}