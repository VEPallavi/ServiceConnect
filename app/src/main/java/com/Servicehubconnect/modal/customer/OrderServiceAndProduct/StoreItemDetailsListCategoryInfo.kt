package com.Servicehubconnect.modal.customer.OrderServiceAndProduct

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class StoreItemDetailsListCategoryInfo{

    @SerializedName("_id")
    @Expose
    public var _id: String? = null

    @SerializedName("product_name")
    @Expose
    public var product_name: String? = null

    @SerializedName("service_name")
    @Expose
    public var service_name: String? = null


    @SerializedName("category_id")
    @Expose
    public var category_id: String? = null




    @SerializedName("extra_products")
    @Expose
    public var extra_products: String? = null

    @SerializedName("extra_services")
    @Expose
    public var extra_services: String? = null

    @SerializedName("description")
    @Expose
    public var description: String? = null


    @SerializedName("size_price")
    @Expose
    public var size_price: ArrayList<SizePriceForProduct> = ArrayList()


    @SerializedName("size_price_duration")
    @Expose
    public var size_price_duration: ArrayList<SizePriceDurationForService> = ArrayList()

    @SerializedName("business_id")
    @Expose
    public var business_id: String? = null

    @SerializedName("product_image")
    @Expose
    public var product_image: String? = null


    @SerializedName("service_image")
    @Expose
    public var service_image: String? = null



    @SerializedName("extraProductInfo")
    @Expose
    public var extraProductInfo: ArrayList<ExtraProductInfo> = ArrayList()


    @SerializedName("extraServiceInfo")
    @Expose
    public var extraServiceInfo: ArrayList<ExtraServiceInfo> = ArrayList()



    @SerializedName("is_size")
    @Expose
    public var is_size: Boolean? = null


    @SerializedName("isExtraPackage")
    @Expose
    public var isExtraPackage: Boolean? = null

}

//(
//
//
//
//
//        var _id: String,
//        var product_name: String,
//        var service_name: String,
//        var category_id: String,
//        var extra_products: String,
//        var extra_services: String,
//        var description: String,
//        var size_price: ArrayList<SizePriceForProduct> = ArrayList(),
//        var size_price_duration: ArrayList<SizePriceDurationForService> = ArrayList(),
//        var business_id: String,
//        var product_image: String,
//        var service_image: String,
//        var extraProductInfo: ArrayList<ExtraProductInfo> = ArrayList(),
//        var extraServiceInfo: ArrayList<ExtraServiceInfo> = ArrayList(),
//        var is_size: Boolean,
//        var isExtraPackage: Boolean
//
//)