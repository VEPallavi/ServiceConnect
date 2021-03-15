package com.Servicehubconnect.modal.customer.OrderServiceAndProduct

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlin.collections.ArrayList

public class ExtraPackageDetailsResponseModel {

    @SerializedName("extraInformation")
    @Expose
    private var extraInformation:  ArrayList<ExtraPackageListModel>?= null


    fun getExtraInformation(): ArrayList<ExtraPackageListModel>? {
        return extraInformation
    }

    fun setExtraInformation(extraInformation: ArrayList<ExtraPackageListModel>?) {
        this.extraInformation = extraInformation
    }


}