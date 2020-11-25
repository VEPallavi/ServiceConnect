package com.Servicehubconnect.adapter.customerApp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.Servicehubconnect.R


class MyRatingAndCommentAdapter(var mContext: Context) : RecyclerView.Adapter<MyRatingAndCommentAdapter.MyRatingCommentViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRatingAndCommentAdapter.MyRatingCommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item_row_list_myrating_and_comment,parent,false)
        return MyRatingCommentViewHolder(view)
    }


    override fun getItemCount(): Int {
        return 10
    }


    override fun onBindViewHolder(holder: MyRatingAndCommentAdapter.MyRatingCommentViewHolder, position: Int) {

    }



    inner class MyRatingCommentViewHolder(view: View): RecyclerView.ViewHolder(view){
        var ivPic: ImageView
        var tvProfessionName: TextView
        var tvBusinessName: TextView
        var tvCommentsDescp: TextView

        init {
            ivPic = view.findViewById(R.id.iv_pic)
            tvProfessionName = view.findViewById(R.id.tv_profession_name)
            tvBusinessName = view.findViewById(R.id.tv_business_name)
            tvCommentsDescp = view.findViewById(R.id.tv_comments_descp)
        }



    }


}