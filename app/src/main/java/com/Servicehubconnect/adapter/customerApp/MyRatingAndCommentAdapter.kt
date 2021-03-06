package com.Servicehubconnect.adapter.customerApp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.Servicehubconnect.R
import com.Servicehubconnect.modal.customer.RatingAndCommentModal
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class MyRatingAndCommentAdapter(var mContext: Context, var dataList: ArrayList<RatingAndCommentModal>) : RecyclerView.Adapter<MyRatingAndCommentAdapter.MyRatingCommentViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRatingAndCommentAdapter.MyRatingCommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item_row_list_myrating_and_comment,parent,false)
        return MyRatingCommentViewHolder(view)
    }


    override fun getItemCount(): Int {
        return dataList.size
    }


    override fun onBindViewHolder(holder: MyRatingAndCommentAdapter.MyRatingCommentViewHolder, position: Int) {

        holder.bindItems(dataList.get(position))
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


        fun bindItems(dataModal: RatingAndCommentModal){
            Glide.with(mContext)
                    .load(dataModal.professional_profile)
                    .apply(RequestOptions().placeholder(R.drawable.dummy).error(R.drawable.dummy))
                    .into(ivPic)

            tvProfessionName.setText(dataModal.professionalName)
            tvBusinessName.setText(dataModal.professional_bussinessName)
            tvCommentsDescp.setText(dataModal.comment)
        }
    }


}