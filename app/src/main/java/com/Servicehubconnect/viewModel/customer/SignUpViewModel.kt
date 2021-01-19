package com.Servicehubconnect.viewModel.customer

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.Servicehubconnect.helper.Utils
import com.freeluancer.seeker.network.ApiClient
import com.freeluancer.seeker.network.ApiService
import com.google.gson.JsonObject
import okhttp3.internal.Util
import retrofit2.Call
import retrofit2.Response
import kotlin.math.sign


class SignUpViewModel : ViewModel(){
    var signUpResult: MutableLiveData<JsonObject>?= null



    fun signUpData(mContext: Context, name: String, emailId: String, password: String
                   , phoneNumber: String, localCity: String, referralCode: String, countryCode: String): MutableLiveData<JsonObject>
    {

        if (signUpResult == null) {
            signUpResult = MutableLiveData()
        }

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.signUpUser(name, emailId, password, phoneNumber, countryCode, localCity, referralCode)

        Utils.showProgressDialog(mContext)

        call.enqueue(object: retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response != null && response.body()!= null){
                    signUpResult!!.value = response.body()
                }
            }
        })
        return signUpResult!!
    }



}