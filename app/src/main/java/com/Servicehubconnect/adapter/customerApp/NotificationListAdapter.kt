package com.Servicehubconnect.adapter.customerApp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.Servicehubconnect.R


class NotificationListAdapter (var mContext : Context) : RecyclerView.Adapter<NotificationListAdapter.NotificationViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationListAdapter.NotificationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item_row_notification_list,parent,false)
        return NotificationViewHolder(view)
    }



    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: NotificationListAdapter.NotificationViewHolder, position: Int) {

    }

    class NotificationViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var tv_notification_title: TextView
        var tv_notificationTime: TextView
        var tv_notification_description: TextView

        init {
            tv_notification_title = itemView.findViewById(R.id.tvNotificationTitle)
            tv_notificationTime = itemView.findViewById(R.id.tv_dateTimeText)
            tv_notification_description = itemView.findViewById(R.id.tvNotificationMessage)
        }


    }
}