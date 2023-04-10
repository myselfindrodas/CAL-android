package com.app.conectar.retrofit

import com.app.conectar.model.edittruck_details.EditTruckDetailsRequestModel


class ApiHelper(private val apiInterface: ApiInterface) {

    suspend fun login(
        email: String,
        password: String,
        source: String,
        device_token: String,
        device_type: String
    ) =
        apiInterface.login(email, password, source, device_token, device_type)

    suspend fun loginwithphone(
        country_code: String,
        mobile: String,
        source: String,
        device_token: String,
        device_type: String
    ) =
        apiInterface.loginwithphone(country_code, mobile, source, device_token, device_type)

    suspend fun getUserProfileDetails(userId: String) = apiInterface.getProfileDetails(userId)

    suspend fun resendotp(user_master_id: String, email: String, mobile: String) =
        apiInterface.resendotp(user_master_id, email, mobile)

    suspend fun verifyuser(user_master_id: String, otp: String) =
        apiInterface.verifyuser(user_master_id, otp)

    suspend fun fleetnewjoblist(user_master_id: String) =
        apiInterface.fleetnewjoblist(user_master_id)

    suspend fun myjob(user_master_id: String) = apiInterface.myjob(user_master_id)
    suspend fun notificationlist(user_master_id: String) =
        apiInterface.notificationlist(user_master_id)

    suspend fun getPasswordResetUpdate(password: String, password_token: String) =
        apiInterface.getPasswordResetUpdate(password, password_token)

    suspend fun getForgetPassword(emailId: String) = apiInterface.getForgetPassword(emailId)
    suspend fun getDriverList(userId: String) = apiInterface.getDriverList(userId)
    suspend fun getTruckDetails(truckId: String) = apiInterface.getTruckDetails(truckId)
    suspend fun getTruckList(userId: String) = apiInterface.getTruckList(userId)
    suspend fun getCityList(state_id: String) = apiInterface.getCityList(state_id)
    suspend fun deleteTruck(truck_id: String) = apiInterface.deleteTruck(truck_id)
    suspend fun logout(user_id: String) = apiInterface.logout(user_id)
    suspend fun getStateList() = apiInterface.getStateList()
    suspend fun getappIntroList() = apiInterface.getappIntroList()

    suspend fun registration(mobile:String, registration_source:String, company_registration:String,
                             fname:String, state:String, email: String, lanme: String, password: String,
                             user_role: String, company_name: String, city: String, latitude:String, longitude:String
    ) = apiInterface.registration(mobile, registration_source, company_registration, fname, state, email, lanme,
    password, user_role, company_name, city, latitude, longitude)

    suspend fun updateTruckDetails(editTruckDetailsRequestModel: EditTruckDetailsRequestModel) =
        apiInterface.updateTruckDetails(
            editTruckDetailsRequestModel.truck_id,
            editTruckDetailsRequestModel.user_master_id,
            editTruckDetailsRequestModel.truck_vin_number,
            editTruckDetailsRequestModel.truck_reg_number,
            editTruckDetailsRequestModel.truck_make,
            editTruckDetailsRequestModel.truck_body_style,
            editTruckDetailsRequestModel.truck_model,
            editTruckDetailsRequestModel.truck_color,
            editTruckDetailsRequestModel.truck_cabin_type,
            editTruckDetailsRequestModel.truck_empty_weight,
            editTruckDetailsRequestModel.truck_carring_capacity,
            editTruckDetailsRequestModel.truck_weight_metric,
            editTruckDetailsRequestModel.truck_type,
            editTruckDetailsRequestModel.company_trucknumber)


}