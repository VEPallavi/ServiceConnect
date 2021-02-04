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
import com.Servicehubconnect.modal.customer.ProfessionalListDataModel


class ProfessionalListAdapter(var mContext: Context, var professionalList: ArrayList<ProfessionalListDataModel>, var itemListener: ItemListener) : RecyclerView.Adapter<ProfessionalListAdapter.ProfessionalListViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessionalListAdapter.ProfessionalListViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item_row_list_professionals, parent, false)
        return ProfessionalListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return professionalList.size
    }

    override fun onBindViewHolder(holder: ProfessionalListAdapter.ProfessionalListViewHolder, position: Int) {
        holder.bindItems(professionalList.get(position))

    }




    inner class ProfessionalListViewHolder(view: View): RecyclerView.ViewHolder(view){
        var iv_business_pic: ImageView
        var tv_professionName: TextView
        var tv_businessName: TextView
        var tv_ratingValue: TextView
        var tv_ratingCount: TextView
        var tv_commentsCount: TextView
        var tv_purpose: TextView
        var tv_cities_of_operation: TextView
        var tv_cost: TextView
        var tv_backgroundCheck_yes_no: TextView
        var tv_tradeLicence_yes_no: TextView
        var tv_insurance_yes_no: TextView
        var tv_certificate_yes_no: TextView
        var tv_driving_licence_yes_no: TextView
        var tv_commercial_insured_yes_no: TextView
        var cl_main: ConstraintLayout

        init {
            iv_business_pic = view.findViewById(R.id.iv_business_pic);
            tv_professionName = view.findViewById(R.id.tv_professionName);
            tv_businessName = view.findViewById(R.id.tv_businessName);
            tv_ratingValue = view.findViewById(R.id.tv_ratingValue);
            tv_ratingCount = view.findViewById(R.id.tv_ratingCount);
            tv_commentsCount = view.findViewById(R.id.tv_commentsCount)
            tv_cities_of_operation = view.findViewById(R.id.tv_cities_of_operation)
            tv_purpose = view.findViewById(R.id.tv_purpose)
            tv_cost = view.findViewById(R.id.tv_cost)
            tv_backgroundCheck_yes_no = view.findViewById(R.id.tv_backgroundCheck_yes_no)
            tv_tradeLicence_yes_no = view.findViewById(R.id.tv_tradeLicence_yes_no)
            tv_insurance_yes_no = view.findViewById(R.id.tv_insurance_yes_no)
            tv_certificate_yes_no = view.findViewById(R.id.tv_certificate_yes_no)
            tv_driving_licence_yes_no = view.findViewById(R.id.tv_driving_licence_yes_no)
            tv_commercial_insured_yes_no = view.findViewById(R.id.tv_commercial_insured_yes_no)
            cl_main= view.findViewById(R.id.cl_main)
        }


        fun bindItems(dataModal: ProfessionalListDataModel){

            tv_professionName.setText(dataModal.getName())
            tv_businessName.setText(dataModal.getBussinessName())
            tv_cities_of_operation.setText(dataModal.getCity())
            tv_ratingValue.setText(""+dataModal.getRatingAverage())


            var totalRating: String =""
            totalRating = dataModal.getTotalRating().toString()

            if(totalRating.length.equals("4")){
                tv_ratingCount.setText(" "+dataModal.getTotalRating() + "(k"+ " Ratings)")
            }
            else{
                tv_ratingCount.setText(" "+dataModal.getTotalRating() + "(Ratings)")
            }

            tv_commentsCount.setText(""+dataModal.getTotalComment())

            if(dataModal.getBackgroundCheck() == true){
                tv_backgroundCheck_yes_no.setText("Yes/Cleared")
            }
            else{
                tv_backgroundCheck_yes_no.setText("N/A")
            }


            if(dataModal.getTradeLicence() == true){
                tv_tradeLicence_yes_no.setText("Yes/Cleared")
            }
            else{
                tv_tradeLicence_yes_no.setText("N/A")
            }


            if(dataModal.getInsurance() == true){
                tv_insurance_yes_no.setText("Yes/Cleared")
            }
            else{
                tv_insurance_yes_no.setText("N/A")
            }


            if(dataModal.getCertificateCheck() == true){
                tv_certificate_yes_no.setText("Yes/Cleared")
            }
            else{
                tv_certificate_yes_no.setText("N/A")
            }


            if(dataModal.getDriverLicence() == true){
                tv_driving_licence_yes_no.setText("Yes/Cleared")
            }
            else{
                tv_driving_licence_yes_no.setText("N/A")
            }


            if(dataModal.getCommercialInsurance() == true){
                tv_commercial_insured_yes_no.setText("Yes/Cleared")
            }
            else{
                tv_commercial_insured_yes_no.setText("N/A")
            }

            tv_purpose.setText(dataModal.getProfessionalPurpose())
            tv_cost.setText(""+ dataModal.getBussinessInfo()?.getCurrencySymbol()+ dataModal.getMinCost())


         cl_main.setOnClickListener {
             itemListener.itemListener(dataModal)
         }

        }


    }

}