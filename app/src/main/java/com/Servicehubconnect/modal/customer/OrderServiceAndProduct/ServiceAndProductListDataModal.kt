package com.Servicehubconnect.modal.customer.OrderServiceAndProduct




data class ServiceAndProductListDataModal (

        var _id: String,
        var category_name: String,
        var business_id: String,
        var category_type: String,
        var createdAt: String,
        var info: ArrayList<CategoryInfo>

)