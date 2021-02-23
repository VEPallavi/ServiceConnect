package com.Servicehubconnect.modal.customer.OrderServiceAndProduct

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




 class SizePriceDurationForService{

    @SerializedName("size")
    @Expose
    private var size: String? = null

    @SerializedName("price")
    @Expose
    private var price: Int? = null

    @SerializedName("duration")
    @Expose
    private var duration: String? = null

    @SerializedName("unit")
    @Expose
    private var unit: String? = null

    fun getSize(): String? {
        return size
    }

    fun setSize(size: String?) {
        this.size = size
    }

    fun getPrice(): Int? {
        return price
    }

    fun setPrice(price: Int?) {
        this.price = price
    }

    fun getDuration(): String? {
        return duration
    }

    fun setDuration(duration: String?) {
        this.duration = duration
    }

    fun getUnit(): String? {
        return unit
    }

    fun setUnit(unit: String?) {
        this.unit = unit
    }

}




//(
//
//        var size: String,
//        var price: Int,
//        var duration: String,
//        var unit: String,
//        var selected: Boolean = false
//
//)

