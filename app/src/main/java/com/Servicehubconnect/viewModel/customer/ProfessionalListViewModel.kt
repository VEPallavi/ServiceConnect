package com.Servicehubconnect.viewModel.customer

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.Servicehubconnect.helper.Utils
import com.Servicehubconnect.network.ApiClient
import com.Servicehubconnect.network.ApiService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response


class ProfessionalListViewModel : ViewModel(){
    var professionalListResult: MutableLiveData<JsonObject>?= null



    fun getProfessionalList(mContext: Context, subCategoryId: String, longitude: String, latitude: String, country: String
                            , city: String, keyword: String): MutableLiveData<JsonObject>{
        professionalListResult = MutableLiveData()
        var subCategoryId = "600e6aeffb9991656e1317fd"
        var latitude = "26.8467088"
        var longitude = "80.9461592"

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.getProfessionalList(subCategoryId, longitude, latitude, country, city, keyword)

        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response!= null && response.body()!= null){
                    professionalListResult!!.value = response.body()
                }
            }
        })
        return professionalListResult!!
    }










}