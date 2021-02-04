package com.Servicehubconnect.modal.customer

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class BussinessInfoModal {

    @SerializedName("happy_hours")
    @Expose
    private var happyHours: HappyHours? = null


    @SerializedName("currency_symbol")
    @Expose
    private var currency_symbol: String?= null


    @SerializedName("open_time")
    @Expose
    private var openTime: String? = null

    @SerializedName("close_time")
    @Expose
    private var closeTime: String? = null

    @SerializedName("location")
    @Expose
    private var location: String? = null

    fun getHappyHours(): HappyHours? {
        return happyHours
    }

    fun setHappyHours(happyHours: HappyHours?) {
        this.happyHours = happyHours
    }



    fun getCurrencySymbol(): String? {
        return currency_symbol
    }
    fun setCurrencySymbol(currency_symbol: String?) {
        this.currency_symbol = currency_symbol
    }



    fun getOpenTime(): String? {
        return openTime
    }
    fun setOpenTime(openTime: String?) {
        this.openTime = openTime
    }


    fun getCloseTime(): String? {
        return closeTime
    }
    fun setCloseTime(closeTime: String?) {
        this.closeTime = closeTime
    }

    fun getLocation(): String? {
        return location
    }

    fun setLocation(location: String?) {
        this.location = location
    }



}