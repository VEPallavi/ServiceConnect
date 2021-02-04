package com.Servicehubconnect.modal.customer

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName





class HappyHours {

    @SerializedName("start_time")
    @Expose
    private var startTime: String? = null

    @SerializedName("end_time")
    @Expose
    private var endTime: String? = null

    @SerializedName("discount")
    @Expose
    private var discount: Int? = null

    fun getStartTime(): String? {
        return startTime
    }

    fun setStartTime(startTime: String?) {
        this.startTime = startTime
    }

    fun getEndTime(): String? {
        return endTime
    }

    fun setEndTime(endTime: String?) {
        this.endTime = endTime
    }

    fun getDiscount(): Int? {
        return discount
    }

    fun setDiscount(discount: Int?) {
        this.discount = discount
    }


}