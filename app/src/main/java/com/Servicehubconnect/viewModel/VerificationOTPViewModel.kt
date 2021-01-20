package com.Servicehubconnect.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.Servicehubconnect.helper.Utils
import com.Servicehubconnect.network.ApiClient
import com.Servicehubconnect.network.ApiService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response


class VerificationOTPViewModel : ViewModel(){
    var verificationOTPResult: MutableLiveData<JsonObject>?= null
    var resendOTPResult: MutableLiveData<JsonObject>?= null




    fun getVerificationOTPData(mContext: Context, email: String, otp: String): MutableLiveData<JsonObject>{
        verificationOTPResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.verificationOTP(email, otp)
        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response!= null && response.body()!= null){
                    verificationOTPResult!!.value = response.body()
                }
            }
        })
        return verificationOTPResult!!
    }


    fun resendOTP(mContext: Context, email: String): MutableLiveData<JsonObject>{
        resendOTPResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.resendOTP(email)
        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response!= null && response.body()!= null){
                    resendOTPResult!!.value = response.body()
                }
            }
        })
        return resendOTPResult!!
    }






}