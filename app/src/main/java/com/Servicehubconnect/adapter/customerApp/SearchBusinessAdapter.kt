package com.Servicehubconnect.adapter.customerApp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.Servicehubconnect.R
import com.Servicehubconnect.modal.customer.ProfessionalListDataModel


class SearchBusinessAdapter(var mContext: Context, var dataList: ArrayList<ProfessionalListDataModel>): RecyclerView.Adapter<SearchBusinessAdapter.SearchBusinessViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchBusinessAdapter.SearchBusinessViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item_row_list_business, parent, false)
        return SearchBusinessViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: SearchBusinessAdapter.SearchBusinessViewHolder, position: Int) {
        var dataList = dataList.get(position)
        holder.bindItems(dataList)
    }


    inner class SearchBusinessViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tv_name: TextView

        init {
            tv_name = view.findViewById(R.id.tv_name)
        }

        fun bindItems(dataModal: ProfessionalListDataModel){
            tv_name.setText(dataModal.getBussinessName())
        }
    }

}