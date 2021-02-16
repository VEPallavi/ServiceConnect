package com.Servicehubconnect.adapter.customerApp.OrderServiceAndProduct

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.Servicehubconnect.R


class ServiceAndProductOrderMenuAdapter(var mContext: Context) : RecyclerView.Adapter<ServiceAndProductOrderMenuAdapter.ServiceProductViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceProductViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item_row_list_product_or_service, parent,false)
        return ServiceProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ServiceProductViewHolder, position: Int) {
        TODO("Not yet implemented")
    }




    inner class ServiceProductViewHolder(view: View): RecyclerView.ViewHolder(view){
        var iv_business_image: ImageView
        var tv_name: TextView
        var tv_price: TextView
        var tv_descp: TextView
        var cl_add: ConstraintLayout
        var tv_add: TextView
        var cl_item_minus_count_plus: ConstraintLayout
        var iv_minus: 




    }


}

