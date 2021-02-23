package com.Servicehubconnect.modal.customer.OrderServiceAndProduct

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName





class ServiceAndProductResponseModal{


    @SerializedName("status")
    @Expose
    private var status: Int? = null

    @SerializedName("data")
    @Expose
    private var data: ArrayList<ServiceAndProductListDataModal?>? = null


    fun getStatus(): Int? {
        return status
    }

    fun setStatus(status: Int?) {
        this.status = status
    }

    fun getData(): ArrayList<ServiceAndProductListDataModal?>? {
        return data
    }

    fun setData(data: ArrayList<ServiceAndProductListDataModal?>?) {
        this.data = data
    }


}





//(
//
//        val status:Int,
//        val data: ArrayList<ServiceAndProductListDataModal> = ArrayList()
//
//
//)

