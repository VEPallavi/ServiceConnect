package com.Servicehubconnect.callback

import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.StoreItemDetailsListCategoryInfo

/**
 * Create By Rahul Mangal
 * Project SignupLibrary Screen
 */

interface AddItemListener {


    fun itemListener(dataModel: StoreItemDetailsListCategoryInfo, position: Int, categoryType: String)

}