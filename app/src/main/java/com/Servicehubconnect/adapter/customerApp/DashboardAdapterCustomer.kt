package com.Servicehubconnect.adapter.customerApp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.Servicehubconnect.R
import com.Servicehubconnect.activity.customer.SubCategoryListActivity
import com.Servicehubconnect.modal.customer.CategoryListDataModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class DashboardAdapterCustomer(var mContext: Context, var categoryList: ArrayList<CategoryListDataModel>)
    : RecyclerView.Adapter<DashboardAdapterCustomer.DashboardViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item_row_list_categories, parent,false)
        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        var dataList = categoryList.get(position)

        holder.bindItems(dataList)
    }



    inner class DashboardViewHolder(view: View): RecyclerView.ViewHolder(view){
        var iv_category_image: ImageView
        var tv_category_name: TextView
        var cl_main: ConstraintLayout

        init {
            iv_category_image = view.findViewById(R.id.iv_category_image)
            tv_category_name = view.findViewById(R.id.tv_category_name)
            cl_main = view.findViewById(R.id.cl_main)
        }

        fun bindItems(dataModal: CategoryListDataModel){

            Glide.with(mContext)
                    .load(dataModal.getCategoryImages())
                    .apply(RequestOptions().placeholder(R.drawable.dashboard_homecare_productpick_icon)
                            .error(R.drawable.dashboard_homecare_productpick_icon))
                    .into(iv_category_image)

            tv_category_name.setText(dataModal.getName())


            cl_main.setOnClickListener {
                var intent = Intent(mContext, SubCategoryListActivity::class.java)
                intent.putExtra("categoryId", dataModal.getId())
                intent.putExtra("categoryName", dataModal.getName())
                mContext.startActivity(intent)
            }

        }

    }




}