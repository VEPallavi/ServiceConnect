package com.serviceconnect.fragment.customerApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.serviceconnect.R
import kotlinx.android.synthetic.main.customer_fragment_dashboard.*


class DashboardFragment : Fragment(), View.OnClickListener{


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupClickListener()
    }

    private fun setupClickListener() {
        cl_salon_beauty.setOnClickListener(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.customer_fragment_dashboard, container, false)
        return view
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.cl_salon_beauty ->{

            }

        }
    }


}