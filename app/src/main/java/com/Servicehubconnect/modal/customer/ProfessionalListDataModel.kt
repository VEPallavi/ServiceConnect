package com.Servicehubconnect.modal.customer

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName





class ProfessionalListDataModel {

    @SerializedName("_id")
    @Expose
    private var id: String? = null

    @SerializedName("bussiness_info")
    @Expose
    private var bussinessInfo: BussinessInfoModal? = null

    @SerializedName("country_code")
    @Expose
    private var countryCode: String? = null

    @SerializedName("profile_pic")
    @Expose
    private var profilePic: String? = null


    @SerializedName("professionalPurpose")
    @Expose
    private var professionalPurpose: String?= null


    @SerializedName("city")
    @Expose
    private var city: String? = null

    @SerializedName("country")
    @Expose
    private var country: String? = null

    @SerializedName("description")
    @Expose
    private var description: String? = null

    @SerializedName("bussiness_name")
    @Expose
    private var bussinessName: String? = null



    @SerializedName("bussinessId")
    @Expose
    private var bussinessId: String? = null



    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("email")
    @Expose
    private var email: String? = null

    @SerializedName("mobile_no")
    @Expose
    private var mobileNo: String? = null

    @SerializedName("backgroundCheck")
    @Expose
    private var backgroundCheck: Boolean? = null

    @SerializedName("certificate_check")
    @Expose
    private var certificateCheck: Boolean? = null

    @SerializedName("commercial_insurance")
    @Expose
    private var commercialInsurance: Boolean? = null

    @SerializedName("driver_licence")
    @Expose
    private var driverLicence: Boolean? = null

    @SerializedName("insurance")
    @Expose
    private var insurance: Boolean? = null


    @SerializedName("insured")
    @Expose
    private var insured: Boolean? = null

    @SerializedName("tradeLicence")
    @Expose
    private var tradeLicence: Boolean? = null


    @SerializedName("min_cost")
    @Expose
    private var min_cost: Int?= null


    @SerializedName("totalComment")
    @Expose
    private var totalComment: Int? = null

    @SerializedName("totalRating")
    @Expose
    private var totalRating: Int? = null

    @SerializedName("ratingAverage")
    @Expose
    private var ratingAverage: Double? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getBussinessInfo(): BussinessInfoModal? {
        return bussinessInfo
    }

    fun setBussinessInfo(bussinessInfo: BussinessInfoModal?) {
        this.bussinessInfo = bussinessInfo
    }

    fun getCountryCode(): String? {
        return countryCode
    }

    fun setCountryCode(countryCode: String?) {
        this.countryCode = countryCode
    }


    fun getProfessionalPurpose(): String? {
        return professionalPurpose
    }

    fun setProfessionalPurpose(professionalPurpose: String?) {
        this.professionalPurpose = professionalPurpose
    }



    fun getProfilePic(): String? {
        return profilePic
    }

    fun setProfilePic(profilePic: String?) {
        this.profilePic = profilePic
    }






    fun getCity(): String? {
        return city
    }

    fun setCity(city: String?) {
        this.city = city
    }

    fun getCountry(): String? {
        return country
    }

    fun setCountry(country: String?) {
        this.country = country
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }


    fun getBussinessName(): String? {
        return bussinessName
    }

    fun setBussinessName(bussinessName: String?) {
        this.bussinessName = bussinessName
    }



    fun getBussinessId(): String? {
        return bussinessId
    }

    fun setBussinessId(bussinessId: String?) {
        this.bussinessId = bussinessId
    }



    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getMobileNo(): String? {
        return mobileNo
    }

    fun setMobileNo(mobileNo: String?) {
        this.mobileNo = mobileNo
    }

    fun getBackgroundCheck(): Boolean? {
        return backgroundCheck
    }

    fun setBackgroundCheck(backgroundCheck: Boolean?) {
        this.backgroundCheck = backgroundCheck
    }

    fun getCertificateCheck(): Boolean? {
        return certificateCheck
    }

    fun setCertificateCheck(certificateCheck: Boolean?) {
        this.certificateCheck = certificateCheck
    }

    fun getCommercialInsurance(): Boolean? {
        return commercialInsurance
    }

    fun setCommercialInsurance(commercialInsurance: Boolean?) {
        this.commercialInsurance = commercialInsurance
    }

    fun getDriverLicence(): Boolean? {
        return driverLicence
    }

    fun setDriverLicence(driverLicence: Boolean?) {
        this.driverLicence = driverLicence
    }

    fun getInsurance(): Boolean? {
        return insurance
    }

    fun setInsurance(insurance: Boolean?) {
        this.insurance = insurance
    }


    fun getInsured(): Boolean? {
        return insured
    }

    fun setInsured(insured: Boolean?) {
        this.insured = insured
    }



    fun getTradeLicence(): Boolean? {
        return tradeLicence
    }

    fun setTradeLicence(tradeLicence: Boolean?) {
        this.tradeLicence = tradeLicence
    }

    fun getMinCost(): Int? {
        return min_cost
    }

    fun setMinCost(min_cost: Int?) {
        this.min_cost = min_cost
    }

    fun getTotalComment(): Int? {
        return totalComment
    }

    fun setTotalComment(totalComment: Int?) {
        this.totalComment = totalComment
    }

    fun getTotalRating(): Int? {
        return totalRating
    }

    fun setTotalRating(totalRating: Int?) {
        this.totalRating = totalRating
    }

    fun getRatingAverage(): Double? {
        return ratingAverage
    }

    fun setRatingAverage(ratingAverage: Double?) {
        this.ratingAverage = ratingAverage
    }


}