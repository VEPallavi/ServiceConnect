package com.serviceconnect.adapter.customerApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.serviceconnect.R


class TimeListAdapter : RecyclerView.Adapter<TimeListAdapter.TimeListViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeListAdapter.TimeListViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item_row_list_time, parent,false)
        return TimeListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: TimeListAdapter.TimeListViewHolder, position: Int) {

    }


    inner class TimeListViewHolder(view: View): RecyclerView.ViewHolder(view){


    }

}