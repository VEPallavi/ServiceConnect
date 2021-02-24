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


class ExtraPackageListViewModel : ViewModel(){
    var extraPackageListResult: MutableLiveData<JsonObject>?= null






    fun getExtraPackageList(mContext: Context, extraId: String, category_type: String): MutableLiveData<JsonObject>{
        extraPackageListResult= MutableLiveData()


        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.getExtraPackageList(extraId, category_type)

        Utils.showProgressDialog(mContext)

        call.enqueue(object: retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()

                if(response!= null && response.body()!= null){
                    extraPackageListResult!!.value = response.body()

                }

            }
        })

        return extraPackageListResult!!
    }




}

