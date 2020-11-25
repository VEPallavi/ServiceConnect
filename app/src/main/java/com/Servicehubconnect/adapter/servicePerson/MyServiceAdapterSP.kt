package com.Servicehubconnect.adapter.servicePerson

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.Servicehubconnect.R


class MyServiceAdapterSP(var mContext: Context) : RecyclerView.Adapter<MyServiceAdapterSP.MyServiceViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyServiceAdapterSP.MyServiceViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.sp_item_row_list_my_service, parent,false)
        return MyServiceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: MyServiceAdapterSP.MyServiceViewHolder, position: Int) {

    }



    inner class MyServiceViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tv_serviceID: TextView
        var iv_pic: ImageView
        var tv_profession_name: TextView
        var tv_business_name: TextView
        var tv_address: TextView
        var tv_datee: TextView
        var tv_time: TextView
        var tv_totalAmount: TextView
        var tv_stattus: TextView

        init {
            tv_serviceID = view.findViewById(R.id.tv_serviceID)
            iv_pic = view.findViewById(R.id.iv_pic)
            tv_profession_name = view.findViewById(R.id.tv_profession_name)

            tv_business_name = view.findViewById(R.id.tv_business_name)
            tv_address = view.findViewById(R.id.tv_address)
            tv_datee = view.findViewById(R.id.tv_datee)

            tv_time = view.findViewById(R.id.tv_time)
            tv_totalAmount = view.findViewById(R.id.tv_totalAmount)
            tv_stattus = view.findViewById(R.id.tv_stattus)
        }


    }

}