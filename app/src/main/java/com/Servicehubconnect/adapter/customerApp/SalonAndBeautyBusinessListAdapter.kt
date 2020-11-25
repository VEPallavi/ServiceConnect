package com.Servicehubconnect.adapter.customerApp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.Servicehubconnect.R
import com.Servicehubconnect.callback.ItemListener


class SalonAndBeautyBusinessListAdapter(var mContext: Context, var itemListener: ItemListener) : RecyclerView.Adapter<SalonAndBeautyBusinessListAdapter.SalonAndBeautyBusinessListViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalonAndBeautyBusinessListAdapter.SalonAndBeautyBusinessListViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item_row_list_business, parent, false)
        return SalonAndBeautyBusinessListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: SalonAndBeautyBusinessListAdapter.SalonAndBeautyBusinessListViewHolder, position: Int) {
        holder.bindItems(position)
    }




    inner class SalonAndBeautyBusinessListViewHolder(view: View): RecyclerView.ViewHolder(view){
        var iv_business_pic: ImageView
        var tv_professionName: TextView
        var tv_businessName: TextView
        var tv_ratingValue: TextView
        var tv_ratingCount: TextView
        var tv_commentsCount: TextView
        var iv_licence_pic: ImageView
        var tv_address: TextView
        var cl_main: ConstraintLayout

        init {
            iv_business_pic = view.findViewById(R.id.iv_business_pic);
            tv_professionName = view.findViewById(R.id.tv_professionName);
            tv_businessName = view.findViewById(R.id.tv_businessName);
            tv_ratingValue = view.findViewById(R.id.tv_ratingValue);
            tv_ratingCount = view.findViewById(R.id.tv_ratingCount);
            tv_commentsCount = view.findViewById(R.id.tv_commentsCount);
            iv_licence_pic = view.findViewById(R.id.iv_licence_pic);
            tv_address = view.findViewById(R.id.tv_address);
            cl_main= view.findViewById(R.id.cl_main)
        }


        fun bindItems(dataModal: Int){

         cl_main.setOnClickListener {
             itemListener.itemListener(dataModal)
         }

        }


    }

}