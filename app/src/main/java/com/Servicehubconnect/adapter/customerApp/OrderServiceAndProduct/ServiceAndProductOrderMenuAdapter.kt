package com.Servicehubconnect.adapter.customerApp.OrderServiceAndProduct

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.Servicehubconnect.R
import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.CategoryInfo


public class ServiceAndProductOrderMenuAdapter(var mContext: Context, var categoryInfoList: ArrayList<CategoryInfo>
                                        ,var categoryType: String)
    : RecyclerView.Adapter<ServiceAndProductOrderMenuAdapter.ServiceProductViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceProductViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item_row_list_product_or_service, parent,false)
        return ServiceProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (categoryInfoList != null) {
            categoryInfoList.size
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: ServiceProductViewHolder, position: Int) {
//        categoryType : "product' for product   & "service' for service
        if(categoryType.equals("product")){
            holder.tv_name.setText(categoryInfoList.get(position).product_name)

        }
        else if(categoryType.equals("service")){
            holder.tv_name.setText(categoryInfoList.get(position).service_name)
        }


    }




    inner class ServiceProductViewHolder(view: View): RecyclerView.ViewHolder(view){
        var iv_business_image: ImageView
        var tv_name: TextView
        var tv_price: TextView
        var tv_descp: TextView
        var cl_add: ConstraintLayout
        var tv_add: TextView
        var cl_item_minus_count_plus: ConstraintLayout
        var iv_minus: ImageView
        var tv_item_count: TextView
        var iv_plus: ImageView


        init {
            iv_business_image = view.findViewById(R.id.iv_business_image)
            tv_name = view.findViewById(R.id.tv_name)
            tv_price = view.findViewById(R.id.tv_price)
            tv_descp = view.findViewById(R.id.tv_descp)
            cl_add = view.findViewById(R.id.cl_add)
            tv_add = view.findViewById(R.id.tv_add)
            cl_item_minus_count_plus = view.findViewById(R.id.cl_item_minus_count_plus)
            iv_minus = view.findViewById(R.id.iv_minus)
            tv_item_count = view.findViewById(R.id.tv_item_count)
            iv_plus = view.findViewById(R.id.iv_plus)
        }


    }


}

