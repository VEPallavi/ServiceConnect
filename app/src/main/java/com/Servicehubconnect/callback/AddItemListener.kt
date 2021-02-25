package com.Servicehubconnect.callback

import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.StoreItemDetailsListCategoryInfo

/**
 * Create By Rahul Mangal
 * Project SignupLibrary Screen
 */

interface AddItemListener {


    fun addItemListener(dataModel: StoreItemDetailsListCategoryInfo, position: Int, categoryType: String)

}