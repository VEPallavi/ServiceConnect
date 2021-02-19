package com.Servicehubconnect.modal.customer.OrderServiceAndProduct




class StoreItemDetailsListCategoryInfo (

        var _id: String,
        var product_name: String,
        var service_name: String,
        var category_id: String,
        var extra_products: String,
        var extra_services: String,
        var description: String,
        var size_price: ArrayList<SizePriceForProduct> = ArrayList(),
        var size_price_duration: ArrayList<SizePriceDurationForService> = ArrayList(),
        var business_id: String,
        var product_image: String,
        var service_image: String,
        var extraProductInfo: ArrayList<ExtraProductInfo> = ArrayList(),
        var extraServiceInfo: ArrayList<ExtraServiceInfo> = ArrayList()

)