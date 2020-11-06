package com.serviceconnect.adapter.customerApp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.serviceconnect.R


class LicenceListAdapter(var mContext: Context) : RecyclerView.Adapter<LicenceListAdapter.LicenceViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LicenceListAdapter.LicenceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item_row_list_licence,parent,false)
        return LicenceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: LicenceListAdapter.LicenceViewHolder, position: Int) {

    }



    inner class LicenceViewHolder(view: View):RecyclerView.ViewHolder(view){
        var iv_pic: ImageView


        init {
            iv_pic = view.findViewById(R.id.iv_pic)
        }

    }

}