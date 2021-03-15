package com.Servicehubconnect.modal.customer.OrderServiceAndProduct

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ExtraPackageListModel {

    @SerializedName("multi_selected")
    @Expose
    private var multi_selected: Boolean? = null


    @SerializedName("dataList")
    @Expose
    private var dataList: ArrayList<ExtraPackageDetailsModel>? = null



    fun getMultiSelected(): Boolean? {
        return multi_selected
    }
    fun setMultiSelected(multi_selected: Boolean?) {
        this.multi_selected = multi_selected
    }



    fun getExtraPackageDetailList(): ArrayList<ExtraPackageDetailsModel>? {
        return dataList
    }
    fun setExtraPackageDetailList(dataList: ArrayList<ExtraPackageDetailsModel>?) {
        this.dataList = dataList
    }



}