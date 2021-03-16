package com.Servicehubconnect.viewModel.customer

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.Servicehubconnect.helper.Utils
import com.Servicehubconnect.network.ApiClient
import com.Servicehubconnect.network.ApiService
import com.google.gson.JsonObject
import okhttp3.internal.Util
import retrofit2.Call
import retrofit2.Response

/**
 * Create By Rahul Mangal
 * Project SignupLibrary Screen
 */

class ReferFriendsViewModel : ViewModel() {
    var referFriendsResult: MutableLiveData<JsonObject>?= null


    fun getReferFriends(mContext: Context): MutableLiveData<JsonObject>{
        referFriendsResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.getReferFriends()

        Utils.showProgressDialog(mContext)

        call.enqueue(object: retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
               Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response!= null && response.body()!= null){
                    referFriendsResult!!.value = response.body()
                }
            }
        })

        return referFriendsResult!!
    }




}