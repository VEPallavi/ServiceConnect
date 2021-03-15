package com.Servicehubconnect.modal.customer.OrderServiceAndProduct

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


public class ExtraPackageDetailsModel {


    @SerializedName("_id")
    @Expose
    private var id: String? = null

    @SerializedName("extraService")
    @Expose
    private var extraService: String? = null

    @SerializedName("extraProduct")
    @Expose
    private var extraProduct: String? = null

    @SerializedName("size_price_duration")
    @Expose
    private var sizePriceDurationForExtraService: ArrayList<SizePriceDurationForExtraService?>? = null


    @SerializedName("size_price")
    @Expose
    private var sizePriceForExtraProduct: ArrayList<SizePriceForExtraProduct?>? = null


    @SerializedName("description")
    @Expose
    private var description: String? = null


    @SerializedName("price")
    @Expose
    private var price: String? = null


    @SerializedName("duration")
    @Expose
    private var duration: Int? = null


    private var selected = false


    @SerializedName("multi_selected")
    @Expose
    private var multi_selected: Boolean? = null


    @SerializedName("business_id")
    @Expose
    private var businessId: String? = null



    fun getDuration(): Int? {
        return duration
    }

    fun setDuration(duration: Int?) {
        this.duration = duration
    }


    fun getPrice(): String? {
        return price
    }

    fun setPrice(price: String?) {
        this.price = price
    }




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
        return sizePriceDurationForExtraService
    }

    fun setSizePriceDuration(sizePriceDuration: ArrayList<SizePriceDurationForExtraService?>?) {
        this.sizePriceDurationForExtraService = sizePriceDuration
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }


    fun isSelected(): Boolean {
        return selected
    }

    fun setSelected(selected: Boolean) {
        this.selected = selected
    }



    fun getIsMultiSelected(): Boolean? {
        return multi_selected
    }

    fun setIsMultiSelected(multi_selected: Boolean?) {
        this.multi_selected = multi_selected
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
        return sizePriceForExtraProduct
    }

    fun setSizePrice(sizePrice: ArrayList<SizePriceForExtraProduct?>?) {
        this.sizePriceForExtraProduct = sizePrice
    }

}