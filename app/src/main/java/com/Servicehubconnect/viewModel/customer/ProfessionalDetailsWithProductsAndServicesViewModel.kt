package com.Servicehubconnect.viewModel.customer

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.Servicehubconnect.helper.Utils
import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.ServiceAndProductResponseModal
import com.Servicehubconnect.network.ApiClient
import com.Servicehubconnect.network.ApiService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response

/**
 * Create By Rahul Mangal
 * Project SignupLibrary Screen
 */

class ProfessionalDetailsWithProductsAndServicesViewModel : ViewModel(){
    var professionalDetailsResult: MutableLiveData<JsonObject>?= null
    var productAndServiceListResult: MutableLiveData<JsonObject>?= null





    fun getProfessionalDetails(mContext: Context, professionalId: String, time_zone: String): MutableLiveData<JsonObject>{
        professionalDetailsResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.getProfessionalDetails(professionalId, time_zone)

        Utils.showProgressDialog(mContext)

        call.enqueue(object: retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response != null  && response.body()!= null){
                    professionalDetailsResult!!.value = response.body()
                }
            }
        })

        return professionalDetailsResult!!
    }


    fun getProductAndServiceList(mContext: Context, bussinessId: String): MutableLiveData<JsonObject>{
        productAndServiceListResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.getProductAndServiceList(bussinessId)


        Utils.showProgressDialog(mContext)

        call.enqueue(object: retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response != null  && response.body()!= null){
                    productAndServiceListResult!!.value = response.body()
                }
            }
        })

        return productAndServiceListResult!!
    }


}