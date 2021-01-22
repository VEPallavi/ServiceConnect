package com.Servicehubconnect.modal.customer

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class CategoryListDataModel {


    @SerializedName("is_deleted")
    @Expose
    private var isDeleted: Int? = null

    @SerializedName("_id")
    @Expose
    private var id: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("category_images")
    @Expose
    private var categoryImages: String? = null

    @SerializedName("createdAt")
    @Expose
    private var createdAt: String? = null

    @SerializedName("updatedAt")
    @Expose
    private var updatedAt: String? = null

    @SerializedName("__v")
    @Expose
    private var v: Int? = null

    fun getIsDeleted(): Int? {
        return isDeleted
    }

    fun setIsDeleted(isDeleted: Int?) {
        this.isDeleted = isDeleted
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getCategoryImages(): String? {
        return categoryImages
    }

    fun setCategoryImages(categoryImages: String?) {
        this.categoryImages = categoryImages
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String?) {
        this.createdAt = createdAt
    }

    fun getUpdatedAt(): String? {
        return updatedAt
    }

    fun setUpdatedAt(updatedAt: String?) {
        this.updatedAt = updatedAt
    }

    fun getV(): Int? {
        return v
    }

    fun setV(v: Int?) {
        this.v = v
    }


}






