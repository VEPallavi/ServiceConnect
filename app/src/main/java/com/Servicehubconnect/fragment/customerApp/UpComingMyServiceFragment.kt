package com.Servicehubconnect.fragment.customerApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.Servicehubconnect.R
import com.Servicehubconnect.adapter.customerApp.UpComingMyServiceAdapter
import kotlinx.android.synthetic.main.customer_fragment_myservice_upcoming.*


class UpComingMyServiceFragment : Fragment(){
    var adapter: UpComingMyServiceAdapter?= null




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_upcomingList.setHasFixedSize(true)
        rv_upcomingList.layoutManager = LinearLayoutManager(activity!!)
        rv_upcomingList.adapter = UpComingMyServiceAdapter(activity!!)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.customer_fragment_myservice_upcoming, container, false)
        return view
    }




}