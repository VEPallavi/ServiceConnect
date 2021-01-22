//package com.Servicehubconnect.adapter.customerApp
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.Servicehubconnect.R
//import com.Servicehubconnect.modal.customer.SubCategoriesModal
//
//
//class SpecialityAdapter(var mContext: Context, var dataList: ArrayList<SubCategoriesModal>) : RecyclerView.Adapter<SpecialityAdapter.SpecialityViewHolder>(){
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialityAdapter.SpecialityViewHolder {
//        var view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item_row_list_sub_categories, parent,false)
//        return SpecialityViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return dataList.size
//    }
//
//    override fun onBindViewHolder(holder: SpecialityAdapter.SpecialityViewHolder, position: Int) {
//        var dataList= dataList.get(position)
//
//        holder.bindItems(dataList)
//    }
//
//
//    inner class SpecialityViewHolder(view: View): RecyclerView.ViewHolder(view){
//        var iv_service_pic: ImageView
//        var txt_service_name: TextView
//        init {
//            iv_service_pic = view.findViewById(R.id.iv_service_pic)
//            txt_service_name = view.findViewById(R.id.txt_service_name)
//        }
//        fun bindItems(dataModal: SubCategoriesModal){
//            iv_service_pic.setImageResource(dataModal.servicePic)
//            txt_service_name.text = dataModal.serviceName
//        }
//
//    }
//
//
//}