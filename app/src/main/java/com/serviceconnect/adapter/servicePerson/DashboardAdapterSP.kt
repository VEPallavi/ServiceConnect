package com.serviceconnect.adapter.servicePerson

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.serviceconnect.R


class DashboardAdapterSP(var mContext: Context) : RecyclerView.Adapter<DashboardAdapterSP.DashboardViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardAdapterSP.DashboardViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.sp_item_row_list_upcoming_service, parent,false)
        return DashboardViewHolder(view)
    }



    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: DashboardAdapterSP.DashboardViewHolder, position: Int) {
          holder.bindItems()
    }


    inner class DashboardViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tv_serviceID: TextView
        var tv_name: TextView
        var tv_price: TextView
        var tv_address: TextView
        var tv_datee: TextView
        var tv_time: TextView
        var tv_paymentMethod: TextView
        var tv_reject: TextView
        var tv_accept: TextView
        var clRejectAccept: ConstraintLayout
        var cl_currentService_reschedule: ConstraintLayout

        init {
            tv_serviceID = view.findViewById(R.id.tv_serviceID)
            tv_name = view.findViewById(R.id.tv_name)
            tv_price = view.findViewById(R.id.tv_price)
            tv_address = view.findViewById(R.id.tv_address)
            tv_datee = view.findViewById(R.id.tv_datee)
            tv_time = view.findViewById(R.id.tv_time)
            tv_paymentMethod = view.findViewById(R.id.tv_paymentMethod)
            tv_reject = view.findViewById(R.id.tv_reject)
            tv_accept = view.findViewById(R.id.tv_accept)
            clRejectAccept = view.findViewById(R.id.cl_reject_accept)
            cl_currentService_reschedule = view.findViewById(R.id.cl_currentService_reschedule)
        }

        fun bindItems(){

            tv_accept.setOnClickListener {
                clRejectAccept.visibility = View.GONE
                cl_currentService_reschedule.visibility = View.VISIBLE
            }

        }


    }


}