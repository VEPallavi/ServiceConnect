package com.Servicehubconnect.modal.customer.OrderServiceAndProduct

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class StoreItemDetailsListCategoryInfo{

    @SerializedName("_id")
    @Expose
    private var id: String? = null

    @SerializedName("service_name")
    @Expose
    private var serviceName: String? = null

    @SerializedName("category_id")
    @Expose
    private var categoryId: String? = null

    @SerializedName("description")
    @Expose
    private var description: String? = null

    @SerializedName("size_price_duration")
    @Expose
    private var sizePriceDuration: ArrayList<SizePriceDurationForService?>? = null

    @SerializedName("business_id")
    @Expose
    private var businessId: String? = null

    @SerializedName("service_image")
    @Expose
    private var serviceImage: String? = null

    @SerializedName("price")
    @Expose
    private var price: String? = null

    @SerializedName("extraId")
    @Expose
    private var extraId: String? = null

    @SerializedName("is_size")
    @Expose
    private var isSize: Boolean? = null

    @SerializedName("extraInfo")
    @Expose
    private var extraInfo: ArrayList<ExtraInfoForProductAndService?>? = null

    @SerializedName("isExtraPackage")
    @Expose
    private var isExtraPackage: Boolean? = null

    @SerializedName("product_name")
    @Expose
    private var productName: String? = null

    @SerializedName("size_price")
    @Expose
    private var sizePrice: ArrayList<SizePriceForProduct?>? = null

    @SerializedName("product_image")
    @Expose
    private var productImage: String? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getServiceName(): String? {
        return serviceName
    }

    fun setServiceName(serviceName: String?) {
        this.serviceName = serviceName
    }

    fun getCategoryId(): String? {
        return categoryId
    }

    fun setCategoryId(categoryId: String?) {
        this.categoryId = categoryId
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getSizePriceDuration(): ArrayList<SizePriceDurationForService?>? {
        return sizePriceDuration
    }

    fun setSizePriceDuration(sizePriceDuration: ArrayList<SizePriceDurationForService?>?) {
        this.sizePriceDuration = sizePriceDuration
    }

    fun getBusinessId(): String? {
        return businessId
    }

    fun setBusinessId(businessId: String?) {
        this.businessId = businessId
    }

    fun getServiceImage(): String? {
        return serviceImage
    }

    fun setServiceImage(serviceImage: String?) {
        this.serviceImage = serviceImage
    }

    fun getPrice(): String? {
        return price
    }

    fun setPrice(price: String?) {
        this.price = price
    }

    fun getExtraId(): String? {
        return extraId
    }

    fun setExtraId(extraId: String?) {
        this.extraId = extraId
    }

    fun getIsSize(): Boolean? {
        return isSize
    }

    fun setIsSize(isSize: Boolean?) {
        this.isSize = isSize
    }

    fun getExtraInfo(): ArrayList<ExtraInfoForProductAndService?>? {
        return extraInfo
    }

    fun setExtraInfo(extraInfo: ArrayList<ExtraInfoForProductAndService?>?) {
        this.extraInfo = extraInfo
    }

    fun getIsExtraPackage(): Boolean? {
        return isExtraPackage
    }

    fun setIsExtraPackage(isExtraPackage: Boolean?) {
        this.isExtraPackage = isExtraPackage
    }

    fun getProductName(): String? {
        return productName
    }

    fun setProductName(productName: String?) {
        this.productName = productName
    }

    fun getSizePrice(): ArrayList<SizePriceForProduct?>? {
        return sizePrice
    }

    fun setSizePrice(sizePrice: ArrayList<SizePriceForProduct?>?) {
        this.sizePrice = sizePrice
    }

    fun getProductImage(): String? {
        return productImage
    }

    fun setProductImage(productImage: String?) {
        this.productImage = productImage
    }



}








//{
//
//    @SerializedName("_id")
//    @Expose
//    public var _id: String? = null
//
//    @SerializedName("product_name")
//    @Expose
//    public var product_name: String? = null
//
//    @SerializedName("service_name")
//    @Expose
//    public var service_name: String? = null
//
//
//    @SerializedName("category_id")
//    @Expose
//    public var category_id: String? = null
//
//
//
//    @SerializedName("price")
//    @Expose
//    public var price: String? = null
//
//
//
//    @SerializedName("extra_products")
//    @Expose
//    public var extra_products: String? = null
//
//    @SerializedName("extra_services")
//    @Expose
//    public var extra_services: String? = null
//
//    @SerializedName("description")
//    @Expose
//    public var description: String? = null
//
//
//    @SerializedName("size_price")
//    @Expose
//    public var size_price: ArrayList<SizePriceForProduct> = ArrayList()
//
//
//    @SerializedName("size_price_duration")
//    @Expose
//    public var size_price_duration: ArrayList<SizePriceDurationForService> = ArrayList()
//
//    @SerializedName("business_id")
//    @Expose
//    public var business_id: String? = null
//
//    @SerializedName("product_image")
//    @Expose
//    public var product_image: String? = null
//
//
//    @SerializedName("service_image")
//    @Expose
//    public var service_image: String? = null
//
//
//
//    @SerializedName("extraInfo")
//    @Expose
//    public var extraInfo: ArrayList<ExtraProductInfo> = ArrayList()
//
//
////    @SerializedName("extraInfo")
////    @Expose
////    public var extraInfo: ArrayList<ExtraServiceInfo> = ArrayList()
//
//
//
//    @SerializedName("is_size")
//    @Expose
//    public var is_size: Boolean? = null
//
//
//    @SerializedName("isExtraPackage")
//    @Expose
//    public var isExtraPackage: Boolean? = null
//
//}

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