package com.Servicehubconnect.adapter.customerApp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.Servicehubconnect.R


class CompletedMyServiceAdapter(var mContext: Context) : RecyclerView.Adapter<CompletedMyServiceAdapter.CompletedViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedMyServiceAdapter.CompletedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item_row_list_upcoming_completed_myservice,parent,false)
        return CompletedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: CompletedMyServiceAdapter.CompletedViewHolder, position: Int) {
        holder.tv_status.text ="Completed"
    }


    inner class CompletedViewHolder(view: View): RecyclerView.ViewHolder(view){
        var iv_pic: ImageView
        var tv_profession_name: TextView
        var tv_business_name: TextView
        var tv_address: TextView
        var tv_status: TextView

        init {
            iv_pic = view.findViewById(R.id.iv_pic)
            tv_profession_name = view.findViewById(R.id.tv_profession_name)
            tv_business_name = view.findViewById(R.id.tv_business_name)
            tv_address = view.findViewById(R.id.tv_address)
            tv_status = view.findViewById(R.id.tv_status)
        }

    }



}