package com.Servicehubconnect.adapter.customerApp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.Servicehubconnect.R


class ServiceItemsAdapter(var mContext: Context) : RecyclerView.Adapter<ServiceItemsAdapter.ServiceItemsViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceItemsAdapter.ServiceItemsViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item_row_list_serviceitems_and_productitems, parent,false)
        return ServiceItemsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ServiceItemsAdapter.ServiceItemsViewHolder, position: Int) {

    }




    inner class ServiceItemsViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tv_item_name: TextView
        var tv_item_price: TextView
        var tv_item_time: TextView


        init {
            tv_item_name = view.findViewById(R.id.tv_item_name)
            tv_item_price = view.findViewById(R.id.tv_item_price)
            tv_item_time = view.findViewById(R.id.tv_item_time)
        }


    }


}