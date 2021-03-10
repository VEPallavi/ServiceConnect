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
import com.Servicehubconnect.helper.Utils
import com.Servicehubconnect.modal.customer.ProfessionalListDataModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


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
        var tv_city: TextView
        var tv_cost: TextView
        var tv_backgroundCheck_yes_no: TextView
        var tv_tradeLicence_yes_no: TextView
        var tv_insured_yes_no: TextView
        var tv_certificate_yes_no: TextView
        var tv_driving_licence_yes_no: TextView
        var tvCommercialInsured: TextView
        var cl_main: ConstraintLayout
        var tv_happy_hours_open_time: TextView
        var tv_happy_hours_close_time: TextView
        var tv_discount_percentage: TextView
        var tv_instruction: TextView
        var cl_happyHours: ConstraintLayout



        init {
            iv_business_pic = view.findViewById(R.id.iv_business_pic);
            tv_professionName = view.findViewById(R.id.tv_professionName);
            tv_businessName = view.findViewById(R.id.tv_businessName);
            tv_ratingValue = view.findViewById(R.id.tv_ratingValue);
            tv_ratingCount = view.findViewById(R.id.tv_ratingCount);
            tv_commentsCount = view.findViewById(R.id.tv_commentsCount)
            tv_city = view.findViewById(R.id.tv_city)
            tv_purpose = view.findViewById(R.id.tv_purpose)
            tv_cost = view.findViewById(R.id.tv_cost)
            tv_backgroundCheck_yes_no = view.findViewById(R.id.tv_backgroundCheck_yes_no)
            tv_tradeLicence_yes_no = view.findViewById(R.id.tv_tradeLicence_yes_no)
            tv_insured_yes_no = view.findViewById(R.id.tv_insured_yes_no)
            tv_certificate_yes_no = view.findViewById(R.id.tv_certificate_yes_no)
            tv_driving_licence_yes_no = view.findViewById(R.id.tv_driving_licence_yes_no)
            tvCommercialInsured = view.findViewById(R.id.tv_commercial_insured_yes_no)
            cl_main= view.findViewById(R.id.cl_main)

            tv_happy_hours_open_time = view.findViewById(R.id.tv_happy_hours_open_time)
            tv_happy_hours_close_time = view.findViewById(R.id.tv_happy_hours_close_time)
            tv_discount_percentage= view.findViewById(R.id.tv_discount_percentage)
            tv_instruction = view.findViewById(R.id.tv_instruction)
            cl_happyHours = view.findViewById(R.id.cl_happyHours)
        }



        fun bindItems(dataModal: ProfessionalListDataModel){

            tv_professionName.setText(dataModal.getName())
            tv_businessName.setText(dataModal.getBussinessName())
            tv_city.setText(dataModal.getLocalCity())
            tv_ratingValue.setText(""+dataModal.getRatingAverage())

            if(dataModal.getCustomerInstructions() != null){
                tv_instruction.setText(dataModal.getCustomerInstructions())
            }



            Glide.with(mContext)
                    .load(dataModal.getProfilePic())
                    .apply(RequestOptions().placeholder(R.drawable.dummy).error(R.drawable.dummy))
                    .into(iv_business_pic)


            if(dataModal.getBussinessInfo()!!.getIsHappyHours() == true){
                cl_happyHours.visibility = View.VISIBLE

                if(dataModal.getBussinessInfo()!!.getHappyHours()!!.getStartTime() != null
                        &&  !dataModal.getBussinessInfo()!!.getHappyHours()!!.getStartTime()!!.equals("")){


                    tv_happy_hours_open_time.setText(Utils.get12hrFormatfrom24hr(dataModal.getBussinessInfo()!!.getHappyHours()!!.getStartTime()!!))
                }

                if(dataModal.getBussinessInfo()!!.getHappyHours()!!.getEndTime() != null
                        &&  !dataModal.getBussinessInfo()!!.getHappyHours()!!.getEndTime()!!.equals("")){



                    tv_happy_hours_close_time.setText(" - "+Utils.get12hrFormatfrom24hr(dataModal.getBussinessInfo()!!.getHappyHours()!!.getEndTime()!!))
                }

                if(dataModal.getBussinessInfo()!!.getHappyHours()!!.getEndTime() != null
                        &&  !dataModal.getBussinessInfo()!!.getHappyHours()!!.getDiscount()!!.equals("")){


                    tv_discount_percentage.setText(""+dataModal.getBussinessInfo()!!.getHappyHours()!!.getDiscount() +"%")
                }

            }
            else{
                cl_happyHours.visibility = View.GONE
            }


            var totalRating: String =""
            totalRating = dataModal.getTotalRating().toString()

            if(totalRating.length.equals("4")){
                tv_ratingCount.setText(" "+dataModal.getTotalRating() + "(k"+ " Ratings)")
            }
            else{
                tv_ratingCount.setText(" "+dataModal.getTotalRating() + "(Ratings)")
            }

            tv_commentsCount.setText(""+dataModal.getTotalComment() +" Comments")





            tv_backgroundCheck_yes_no.setText(dataModal.getBackgroundCheck())
            tv_tradeLicence_yes_no.setText(dataModal.getTradeLicence())
            tv_insured_yes_no.setText(dataModal.getInsured())
            tv_certificate_yes_no.setText(dataModal.getCertificateCheck())
            tv_driving_licence_yes_no.setText(dataModal.getDriverLicence())
            tvCommercialInsured.setText(dataModal.getCommercialInsurance())



//            if(dataModal.getBackgroundCheck() == true){
//                tv_backgroundCheck_yes_no.setText("Yes/Cleared")
//            }
//            else{
//                tv_backgroundCheck_yes_no.setText("N/A")
//            }
//
//
//            if(dataModal.getTradeLicence() == true){
//                tv_tradeLicence_yes_no.setText("Yes/Cleared")
//            }
//            else{
//                tv_tradeLicence_yes_no.setText("N/A")
//            }
//
//
//            if(dataModal.getInsured() == true){
//                tv_insured_yes_no.setText("Yes/Cleared")
//            }
//            else{
//                tv_insured_yes_no.setText("N/A")
//            }
//
//
//            if(dataModal.getCertificateCheck() == true){
//                tv_certificate_yes_no.setText("Yes/Cleared")
//            }
//            else{
//                tv_certificate_yes_no.setText("N/A")
//            }
//
//
//            if(dataModal.getDriverLicence() == true){
//                tv_driving_licence_yes_no.setText("Yes/Cleared")
//            }
//            else{
//                tv_driving_licence_yes_no.setText("N/A")
//            }
//
//
//            if(dataModal.getCommercialInsurance() == true){
//                tvCommercialInsured.setText("Yes/Cleared")
//            }
//            else{
//                tvCommercialInsured.setText("N/A")
//            }



            tv_purpose.setText(dataModal.getProfessionalPurpose())
            tv_cost.setText(""+ dataModal.getBussinessInfo()?.getCurrencySymbol()+ dataModal.getMinCost())


         cl_main.setOnClickListener {
             itemListener.itemListener(dataModal)
         }

        }


    }

}