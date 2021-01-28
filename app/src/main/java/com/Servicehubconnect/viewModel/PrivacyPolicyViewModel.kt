package com.Servicehubconnect.viewModel

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


class PrivacyPolicyViewModel : ViewModel(){
    var privacyPolicyResult: MutableLiveData<JsonObject>?= null
    var submitPrivacyPolicyResult: MutableLiveData<JsonObject>?= null
    var preference: AppPreference?= null



    fun getPrivacyPolicy(mContext: Context): MutableLiveData<JsonObject>{
        privacyPolicyResult = MutableLiveData()
        preference = AppPreference.getInstance(mContext)
        var token = preference!!.getAuthToken()
        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.getPrivacyPolicy("Bearer " +token)

        Utils.showProgressDialog(mContext)

        call.enqueue(object: retrofit2.Callback<JsonObject>{

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response!= null  && response.body()!= null){
                    privacyPolicyResult!!.value = response.body()
                }
            }
        })
        return privacyPolicyResult!!
    }


    fun submitPrivacyPolicy(mContext: Context): MutableLiveData<JsonObject>{
        submitPrivacyPolicyResult = MutableLiveData()
        preference = AppPreference.getInstance(mContext)
        var token = preference!!.getAuthToken()

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.submitPrivacyPolicy("Bearer "+token)

        Utils.showProgressDialog(mContext)

        call.enqueue(object: retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response!= null && response.body()!= null){
                    submitPrivacyPolicyResult!!.value = response.body()
                }
            }
        })
        return submitPrivacyPolicyResult!!
    }



}