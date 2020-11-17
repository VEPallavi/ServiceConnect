package com.serviceconnect.fragment.ServicePerson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.serviceconnect.R
import com.serviceconnect.activity.servicePerson.HomeActivitySP
import com.serviceconnect.adapter.servicePerson.DashboardAdapterSP
import com.serviceconnect.helper.customer.ConstantFragmentName
import kotlinx.android.synthetic.main.sp_fragment_dashboard.*


class DashboardFragmentSP : Fragment(){
    var adapter: DashboardAdapterSP?= null




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_upcomingList.setHasFixedSize(true)
        rv_upcomingList.layoutManager = LinearLayoutManager(activity!!)
        adapter = DashboardAdapterSP(activity!!)
        rv_upcomingList.adapter = adapter
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.sp_fragment_dashboard, container, false)
        updateToolbar()
        return view
    }

    private fun updateToolbar() {
        (activity as HomeActivitySP).setToolbarTitle(ConstantFragmentName.DASHBOARD_FRAGMENT)
        (activity as HomeActivitySP).setToolbarMenuVisibility(true)
        (activity as HomeActivitySP).setToolbarBackVisibility(false)
    }


}