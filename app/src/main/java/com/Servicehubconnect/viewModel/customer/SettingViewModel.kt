package com.Servicehubconnect.viewModel.customer

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.Servicehubconnect.helper.AppPreference
import com.Servicehubconnect.helper.Utils
import com.Servicehubconnect.network.ApiClient
import com.Servicehubconnect.network.ApiService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response


class SettingViewModel : ViewModel(){
    var logOutResult: MutableLiveData<JsonObject>?= null
    var preference: AppPreference?= null



    fun logOutUser(mContext: Context): MutableLiveData<JsonObject>{
        logOutResult = MutableLiveData()
        preference = AppPreference.getInstance(mContext)
        var token = preference!!.getAuthToken()


        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.logOutUser("Bearer "+ token)

        Utils.showProgressDialog(mContext)
        
        call.enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
               Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
               Utils.hideProgressDialog()
                if(response != null && response.body() != null){
                    logOutResult!!.value = response.body()
                }
            }
        })

        return logOutResult!!
    }









}