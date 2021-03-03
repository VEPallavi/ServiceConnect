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


class SearchBusinessViewModel : ViewModel(){
    var searchBusinessResult: MutableLiveData<JsonObject>?= null





    fun searchBusiness(mContext: Context, subCategoryId: String, longitude: String, latitude: String, country: String
                       , city: String, keyword: String, timeZone:String): MutableLiveData<JsonObject>
    {
        searchBusinessResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.getProfessionalList(subCategoryId, longitude, latitude, country, city, keyword, timeZone)

        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)

            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response!= null && response.body()!= null){
                    searchBusinessResult!!.value = response.body()
                }
            }
        })
        return searchBusinessResult!!
    }





}