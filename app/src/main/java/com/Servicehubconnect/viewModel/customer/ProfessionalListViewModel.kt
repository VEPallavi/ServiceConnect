package com.Servicehubconnect.viewModel.customer

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.Servicehubconnect.network.ApiClient
import com.Servicehubconnect.network.ApiService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response


class ProfessionalListViewModel : ViewModel(){
    var professionalListResult: MutableLiveData<JsonObject>?= null




    fun getProfessionalList(mContext: Context, subCategoryId: String, longitude: String, latitude: String, country: String, city: String): MutableLiveData<JsonObject>{
        professionalListResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.getProfessionalList(subCategoryId, longitude, latitude, country, city)

        call.enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                TODO("Not yet implemented")
            }


        })


        return professionalListResult!!
    }










}