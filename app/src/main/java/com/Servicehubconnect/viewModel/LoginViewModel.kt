package com.Servicehubconnect.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.Servicehubconnect.network.ApiClient
import com.Servicehubconnect.network.ApiService
import com.google.gson.JsonObject


class LoginViewModel : ViewModel(){
    var loginResult: MutableLiveData<JsonObject> ?= null




    fun loginUser(mContext: Context, email: String, password: String): MutableLiveData<JsonObject>{
        loginResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.loginUser(email, password)


        return loginResult!!
    }







}