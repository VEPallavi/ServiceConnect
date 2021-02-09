package com.Servicehubconnect.adapter.customerApp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.Servicehubconnect.R
import com.Servicehubconnect.modal.customer.CommentListModal
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class CommentListAdapter(var mContext: Context, var dataList: ArrayList<CommentListModal>) : RecyclerView.Adapter<CommentListAdapter.CommentViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentListAdapter.CommentViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item_list_comment_list, parent,false)
        return CommentViewHolder(view)
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CommentListAdapter.CommentViewHolder, position: Int) {


        holder.bindItems(dataList.get(position))


    }



    inner class CommentViewHolder(view: View): RecyclerView.ViewHolder(view){
        var civ_pic: ImageView
        var tv_name: TextView
        var tv_rating: TextView
        var tv_address: TextView
        var tv_date: TextView
        var tv_comment: TextView



        init {
            civ_pic = view.findViewById(R.id.civ_pic)
            tv_name = view.findViewById(R.id.tv_name)
            tv_rating = view.findViewById(R.id.tv_rating)
            tv_address = view.findViewById(R.id.tv_address)
            tv_date = view.findViewById(R.id.tv_date)
            tv_comment = view.findViewById(R.id.tv_comment)

        }

        fun bindItems(dataModal: CommentListModal){
            tv_name.setText(dataModal.user_name)
            tv_rating.setText(""+dataModal.rating)
            tv_address.setText(dataModal.user_country)
            tv_date.setText(dataModal.createdAt)
            tv_comment.setText(dataModal.comment)


            Glide.with(mContext)
                    .load(dataModal.user_profile_pic)
                    .apply(RequestOptions().placeholder(R.drawable.dummy).error(R.drawable.dummy))
                    .into(civ_pic)


        }






    }



}