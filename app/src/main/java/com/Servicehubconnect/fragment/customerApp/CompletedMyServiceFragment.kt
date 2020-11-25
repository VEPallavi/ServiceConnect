package com.Servicehubconnect.fragment.customerApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.Servicehubconnect.R
import com.Servicehubconnect.adapter.customerApp.CompletedMyServiceAdapter
import kotlinx.android.synthetic.main.customer_fragment_myservice_completed.*



class CompletedMyServiceFragment : Fragment(){
    var adapter: CompletedMyServiceAdapter?= null


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_completedList.setHasFixedSize(true)
        rv_completedList.layoutManager = LinearLayoutManager(activity!!)
        rv_completedList.adapter = CompletedMyServiceAdapter(activity!!)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.customer_fragment_myservice_completed, container, false)
        return view;
    }


}