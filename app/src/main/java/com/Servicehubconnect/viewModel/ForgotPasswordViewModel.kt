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

class ForgotPasswordViewModel : ViewModel(){
    var forgotPasswordResult: MutableLiveData<JsonObject>?= null



    fun getForgotPasswordData(mContext: Context, email: String): MutableLiveData<JsonObject>{
        forgotPasswordResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.forgotPassword(email)

        Utils.showProgressDialog(mContext)

        call.enqueue(object: retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response!= null && response.body()!= null){
                    forgotPasswordResult!!.value = response.body()
                }
            }
        })
        return forgotPasswordResult!!
    }





}