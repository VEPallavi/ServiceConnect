package com.Servicehubconnect.viewModel.customer

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.freeluancer.seeker.network.ApiClient
import com.freeluancer.seeker.network.ApiService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response


class SignUpViewModel : ViewModel(){
    var signUpResult: MutableLiveData<JsonObject>?= null



    fun signUpData(mContext: Context, name: String, emailId: String, password: String
                   , phoneNumber: String, countryCode: String): MutableLiveData<JsonObject>
    {

        if (signUpResult == null) {
            signUpResult = MutableLiveData()
        }
        signUpResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.signUpUser()

        call.enqueue(object: retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                //Utility.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

            }

        })

        return signUpResult!!
    }



}