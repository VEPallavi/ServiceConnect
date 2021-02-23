package com.Servicehubconnect.modal.customer.OrderServiceAndProduct

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




/**
 * Create By Rahul Mangal
 * Project SignupLibrary Screen
 */

class ExtraInfoForProductAndService {

    @SerializedName("_id")
    @Expose
    private var id: String? = null

    @SerializedName("extraService")
    @Expose
    private var extraService: String? = null

    @SerializedName("size_price_duration")
    @Expose
    private var sizePriceDuration: ArrayList<SizePriceDurationForExtraService?>? = null

    @SerializedName("description")
    @Expose
    private var description: String? = null

    @SerializedName("business_id")
    @Expose
    private var businessId: String? = null

    @SerializedName("extraProduct")
    @Expose
    private var extraProduct: String? = null

    @SerializedName("size_price")
    @Expose
    private var sizePrice: ArrayList<SizePriceForExtraProduct?>? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getExtraService(): String? {
        return extraService
    }

    fun setExtraService(extraService: String?) {
        this.extraService = extraService
    }

    fun getSizePriceDuration(): ArrayList<SizePriceDurationForExtraService?>? {
        return sizePriceDuration
    }

    fun setSizePriceDuration(sizePriceDuration: ArrayList<SizePriceDurationForExtraService?>?) {
        this.sizePriceDuration = sizePriceDuration
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getBusinessId(): String? {
        return businessId
    }

    fun setBusinessId(businessId: String?) {
        this.businessId = businessId
    }

    fun getExtraProduct(): String? {
        return extraProduct
    }

    fun setExtraProduct(extraProduct: String?) {
        this.extraProduct = extraProduct
    }

    fun getSizePrice(): ArrayList<SizePriceForExtraProduct?>? {
        return sizePrice
    }

    fun setSizePrice(sizePrice: ArrayList<SizePriceForExtraProduct?>?) {
        this.sizePrice = sizePrice
    }





}