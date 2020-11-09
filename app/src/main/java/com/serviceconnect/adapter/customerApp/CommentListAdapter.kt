package com.serviceconnect.adapter.customerApp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.serviceconnect.R


class CommentListAdapter(var mContext: Context) : RecyclerView.Adapter<CommentListAdapter.CommentViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentListAdapter.CommentViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item_list_comment_list, parent,false)
        return CommentViewHolder(view)
    }


    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: CommentListAdapter.CommentViewHolder, position: Int) {

    }



    inner class CommentViewHolder(view: View): RecyclerView.ViewHolder(view){



    }



}