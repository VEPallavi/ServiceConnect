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

class SalonAndBeautyAdapter(var mContext: Context, var salonBeautyList: ArrayList<SubCategoriesModal>) : RecyclerView.Adapter<SalonAndBeautyAdapter.SalonAndBeautyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalonAndBeautyAdapter.SalonAndBeautyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item_row_list_select_service, parent,false)
        return SalonAndBeautyViewHolder(view)
    }

    override fun getItemCount(): Int {
       return salonBeautyList.size
    }

    override fun onBindViewHolder(holder: SalonAndBeautyAdapter.SalonAndBeautyViewHolder, position: Int) {
        var dataList = salonBeautyList.get(position)
        holder.bindItems(dataList)

    }


    inner class SalonAndBeautyViewHolder(view: View):RecyclerView.ViewHolder(view){
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