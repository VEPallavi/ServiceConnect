package com.serviceconnect.adapter.customerApp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.serviceconnect.R
import com.serviceconnect.modal.customer.SubCategoriesModal


class Automative_AutoRepairAdapter(var mContext: Context, var automativeAutorepairList: ArrayList<SubCategoriesModal>) : RecyclerView.Adapter<Automative_AutoRepairAdapter.MyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Automative_AutoRepairAdapter.MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item_row_list_select_service, parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return automativeAutorepairList.size
    }

    override fun onBindViewHolder(holder: Automative_AutoRepairAdapter.MyViewHolder, position: Int) {
        var dataList= automativeAutorepairList.get(position)

        holder.bindItems(dataList)
    }


    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var iv_service_pic: ImageView
        var txt_service_name: TextView

        init {
            iv_service_pic = view.findViewById(R.id.iv_service_pic)
            txt_service_name = view.findViewById(R.id.txt_service_name)
        }

        fun bindItems(dataModal: SubCategoriesModal){
            iv_service_pic.setImageResource(dataModal.servicePic)
            txt_service_name.text = dataModal.serviceName
        }
    }


}