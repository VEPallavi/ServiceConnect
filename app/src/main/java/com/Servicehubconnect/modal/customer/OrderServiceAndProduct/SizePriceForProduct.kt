package com.Servicehubconnect.modal.customer.OrderServiceAndProduct

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




/**
 * Create By Rahul Mangal
 * Project SignupLibrary Screen
 */

 class SizePriceForProduct {

    @SerializedName("size")
    @Expose
    private var size: String? = null

    @SerializedName("price")
    @Expose
    private var price: Int? = null


    private var selected = false


    fun isSelected(): Boolean {
        return selected
    }

    fun setSelected(selected: Boolean) {
        this.selected = selected
    }


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


}



//(
//        var size: String,
//        var price: Int,
//        var selected: Boolean = false
//
//)