package com.Servicehubconnect.modal.customer.OrderServiceAndProduct

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




 class ServiceAndProductListDataModal {


    @SerializedName("_id")
    @Expose
    private var id: String? = null

    @SerializedName("category_name")
    @Expose
    private var categoryName: String? = null

    @SerializedName("business_id")
    @Expose
    private var businessId: String? = null

    @SerializedName("category_type")
    @Expose
    private var categoryType: String? = null

    @SerializedName("createdAt")
    @Expose
    private var createdAt: String? = null

    @SerializedName("info")
    @Expose
    private var info: ArrayList<StoreItemDetailsListCategoryInfo?>? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getCategoryName(): String? {
        return categoryName
    }

    fun setCategoryName(categoryName: String?) {
        this.categoryName = categoryName
    }

    fun getBusinessId(): String? {
        return businessId
    }

    fun setBusinessId(businessId: String?) {
        this.businessId = businessId
    }

    fun getCategoryType(): String? {
        return categoryType
    }

    fun setCategoryType(categoryType: String?) {
        this.categoryType = categoryType
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String?) {
        this.createdAt = createdAt
    }

    fun getInfo(): ArrayList<StoreItemDetailsListCategoryInfo?>? {
        return info
    }

    fun setInfo(info: ArrayList<StoreItemDetailsListCategoryInfo?>?) {
        this.info = info
    }




}




//(
//
//        var _id: String,
//        var category_name: String,
//        var business_id: String,
//        var category_type: String,
//        var createdAt: String,
//        var info: ArrayList<StoreItemDetailsListCategoryInfo> = ArrayList()
//
//)