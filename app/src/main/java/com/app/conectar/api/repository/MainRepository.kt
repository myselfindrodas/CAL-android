package com.app.conectar.api.repository

import com.app.conectar.model.edittruck_details.EditTruckDetailsRequestModel
import com.app.conectar.retrofit.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun login(email:String, password:String, source:String, device_token:String, device_type:String) =
        apiHelper.login(email, password, source, device_token, device_type )

    suspend fun loginwithphone(country_code:String, mobile:String, source:String, device_token:String, device_type:String) =
        apiHelper.loginwithphone(country_code, mobile, source, device_token, device_type )

    suspend fun getUserProfileDetails(userId:String) = apiHelper.getUserProfileDetails(userId)

    suspend fun resendotp(user_master_id:String, email:String, mobile:String) = apiHelper.resendotp(user_master_id, email, mobile)
    suspend fun verifyuser(user_master_id:String, otp:String) = apiHelper.verifyuser(user_master_id, otp)
    suspend fun fleetnewjoblist(user_master_id:String) = apiHelper.fleetnewjoblist(user_master_id)
    suspend fun myjob(user_master_id:String) = apiHelper.myjob(user_master_id)
    suspend fun notificationlist(user_master_id:String) = apiHelper.notificationlist(user_master_id)
    suspend fun getPasswordResetUpdate(password: String,password_token: String) = apiHelper.getPasswordResetUpdate(password,password_token)
    suspend fun getForgetPassword(emailId:String) = apiHelper.getForgetPassword(emailId)
    suspend fun getDriverList(userId:String) = apiHelper.getDriverList(userId)
    suspend fun getTruckDetails(truckId:String) = apiHelper.getTruckDetails(truckId)
    suspend fun getTruckList(userId:String) = apiHelper.getTruckList(userId)
    suspend fun getStateList() = apiHelper.getStateList()
    suspend fun getappIntroList() = apiHelper.getappIntroList()
    suspend fun getCityList(state_id: String) = apiHelper.getCityList(state_id)
    suspend fun deleteTruck(truck_id: String) = apiHelper.deleteTruck(truck_id)
    suspend fun logout(user_id: String) = apiHelper.logout(user_id)


    suspend fun registration(mobile:String, registration_source:String, company_registration:String,
                             fname:String, state:String, email: String, lanme: String, password: String,
                             user_role: String, company_name: String, city: String, latitude:String, longitude:String
    ) = apiHelper.registration(mobile, registration_source, company_registration, fname, state, email, lanme,
        password, user_role, company_name, city, latitude, longitude)

    suspend fun updateTruckDetails(editTruckDetailsRequestModel: EditTruckDetailsRequestModel) = apiHelper.updateTruckDetails(editTruckDetailsRequestModel)

}