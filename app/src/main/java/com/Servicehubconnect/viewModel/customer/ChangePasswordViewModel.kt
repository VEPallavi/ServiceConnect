package com.Servicehubconnect.viewModel.customer

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject

class ChangePasswordViewModel : ViewModel(){
    var changePasswordResult: MutableLiveData<JsonObject>?= null



    fun changePassword(mContext: Context): MutableLiveData<JsonObject>{
        changePasswordResult = MutableLiveData()






        return changePasswordResult!!
    }




}