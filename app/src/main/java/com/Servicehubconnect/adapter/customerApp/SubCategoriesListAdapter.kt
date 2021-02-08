package com.Servicehubconnect.adapter.customerApp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.Servicehubconnect.R
import com.Servicehubconnect.callback.ItemListener
import com.Servicehubconnect.modal.customer.SubCategoriesModal
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SubCategoriesListAdapter(var mContext: Context, var subCategoriesList: ArrayList<SubCategoriesModal>, var itemListener: ItemListener) : RecyclerView.Adapter<SubCategoriesListAdapter.SalonAndBeautyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoriesListAdapter.SalonAndBeautyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item_row_list_sub_categories, parent,false)
        return SalonAndBeautyViewHolder(view)
    }

    override fun getItemCount(): Int {
       return subCategoriesList.size
    }

    override fun onBindViewHolder(holder: SubCategoriesListAdapter.SalonAndBeautyViewHolder, position: Int) {
        var dataList = subCategoriesList.get(position)
        holder.bindItems(dataList)

    }


    inner class SalonAndBeautyViewHolder(view: View):RecyclerView.ViewHolder(view){
        var ivSubCategoryImage: ImageView
        var tvSubCategoryName: TextView
        var cl_main: ConstraintLayout

        init {
            ivSubCategoryImage = view.findViewById(R.id.iv_subCategoryImage)
            tvSubCategoryName = view.findViewById(R.id.tv_subCategoryName)
            cl_main = view.findViewById(R.id.cl_main)
        }

        fun bindItems(dataModal: SubCategoriesModal){

            Glide.with(mContext)
                    .load(dataModal.sub_category_images)
                    .apply(RequestOptions().placeholder(R.drawable.dashboard_homecare_productpick_icon)
                            .error(R.drawable.dashboard_homecare_productpick_icon))
                    .into(ivSubCategoryImage)

            tvSubCategoryName.text = dataModal.name

            cl_main.setOnClickListener {
                itemListener.itemListener(dataModal)
            }


        }

    }


}