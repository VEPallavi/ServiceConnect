package com.Servicehubconnect.fragment.customerApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.Servicehubconnect.R
import com.Servicehubconnect.activity.customer.HomeActivityCustomer
import com.Servicehubconnect.adapter.customerApp.NotificationListAdapter
import com.Servicehubconnect.helper.customer.ConstantFragmentName
import kotlinx.android.synthetic.main.customer_fragment_notification.*


class NotificationFragment : Fragment(){
    var adapter: NotificationListAdapter?= null




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.customer_fragment_notification, container, false)

        updateToolbar()
        return view
    }

    private fun updateToolbar() {
        (activity as HomeActivityCustomer).setToolbarTitle(ConstantFragmentName.NOTIFICATION_FRAGMENT)
        (activity as HomeActivityCustomer).setToolbarMenuVisibility(false)
        (activity as HomeActivityCustomer).setToolbarBackVisibility(true)
        (activity as HomeActivityCustomer).setToolbarNotificationVisibility(false)
    }


    private fun initView() {
        notificationRecyclerView.setHasFixedSize(true)
        notificationRecyclerView.layoutManager = LinearLayoutManager(activity!!)
        adapter = NotificationListAdapter(activity!!)
        notificationRecyclerView.adapter = adapter
    }


}