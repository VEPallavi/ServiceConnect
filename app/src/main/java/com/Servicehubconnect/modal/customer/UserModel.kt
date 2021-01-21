package com.Servicehubconnect.modal.customer

class UserModel(
        var _id: String,
        var user_type: String,
        var is_business_user_professional_also: Boolean,
        var country_code: String,
        var is_verify: Boolean,
        var refferal_code: String,
        var is_logout: String,
        var is_active: Boolean,
        var profile_pic: String,
        var is_deleted: Int,
        var professionalPurpose: String,
        var local_city: String,
        var city: String,
        var country: String,
        var name: String,
        var email: String,
        var mobile_no: String
)