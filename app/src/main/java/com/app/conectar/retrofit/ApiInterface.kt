package com.app.conectar.retrofit

import com.app.conectar.api.getProfileResponse.GetProfileResponseModel
import com.app.conectar.model.alertresponse.AlertModelResponse
import com.app.conectar.model.appintrolistresponse.AppIntroResponseModel
import com.app.conectar.model.citylistresponse.CityListResponseModel
import com.app.conectar.model.deletetruck.DelteTruckResponseModel
import com.app.conectar.model.edittruck_details.EditTruckDetailsResponseModel
import com.app.conectar.model.forget_password_response.ForgotPasswordResponseModel
import com.app.conectar.model.getdriver_list_fleet.DriverListResponseModel
import com.app.conectar.model.gettruck_list_fleet.TruckListResponseModel
import com.app.conectar.model.login.responsemodel.LoginResponse
import com.app.conectar.model.logoutresponse.LogoutModelResponse
import com.app.conectar.model.myjobsresponse.MyJobResponseModel
import com.app.conectar.model.newjobresponse.JoblistNewResponse
import com.app.conectar.model.otp.OtpResponse
import com.app.conectar.model.registrationresponse.RegistrationResponseModel
import com.app.conectar.model.statelistresponse.StateListResponseModel
import com.app.conectar.model.truck_details_response.TruckDetailsResponseModel
import com.app.conectar.model.verifyuser.VerifyuserResponse
import retrofit2.http.*


interface ApiInterface {


    @FormUrlEncoded
    @POST("user/userlogin")
    suspend fun login(
        @Field("email", encoded = true) email: String?,
        @Field("password", encoded = true) password: String?,
        @Field("source", encoded = true) source: String?,
        @Field("device_token", encoded = true) device_token: String?,
        @Field("device_type", encoded = true) device_type: String?,
    ): LoginResponse


    @FormUrlEncoded
    @POST("user/userlogin_withphone")
    suspend fun loginwithphone(
        @Field("country_code", encoded = true) country_code: String?,
        @Field("mobile", encoded = true) mobile: String?,
        @Field("source", encoded = true) source: String?,
        @Field("device_token", encoded = true) device_token: String?,
        @Field("device_type", encoded = true) device_type: String?,
    ): LoginResponse


    @FormUrlEncoded
    @POST("dashboard/my-profile")
    suspend fun getProfileDetails(
        @Field("user_master_id", encoded = true) userId: String?
    ): GetProfileResponseModel


    @FormUrlEncoded
    @POST("user/resendotp")
    suspend fun resendotp(
        @Field("user_master_id", encoded = true) user_master_id: String?,
        @Field("email", encoded = true) email: String?,
        @Field("mobile", encoded = true) mobile: String?,
    ): OtpResponse


    @FormUrlEncoded
    @POST("user/verifyuser")
    suspend fun verifyuser(
        @Field("user_master_id", encoded = true) user_master_id: String?,
        @Field("otp", encoded = true) otp: String?,
    ): VerifyuserResponse


    @FormUrlEncoded
    @POST("fleet/getusersavejob_list")
    suspend fun fleetnewjoblist(
        @Field("user_master_id", encoded = true) user_master_id: String?,
    ): JoblistNewResponse


    @FormUrlEncoded
    @POST("dashboard/my_jobs")
    suspend fun myjob(
        @Field("user_master_id", encoded = true) user_master_id: String?,
    ): MyJobResponseModel


    @FormUrlEncoded
    @POST("user/notification")
    suspend fun notificationlist(
        @Field("user_master_id", encoded = true) user_master_id: String?,
    ): AlertModelResponse


    @FormUrlEncoded
    @POST("user/password_reset_update")
    suspend fun getPasswordResetUpdate(
        @Field("password", encoded = true) password: String?,
        @Field("password_token", encoded = true) password_token: String?
    ): ForgotPasswordResponseModel


    @FormUrlEncoded
    @POST("user/password_reset_code")
    suspend fun getForgetPassword(
        @Field("cred", encoded = true) emailId: String?
    ): ForgotPasswordResponseModel


    @FormUrlEncoded
    @POST("fleet/manage_drivers")
    suspend fun getDriverList(
        @Field("user_master_id", encoded = true) userId: String?
    ): DriverListResponseModel


    @FormUrlEncoded
    @POST("fleet/truck_details")
    suspend fun getTruckDetails(
        @Field("truck_id", encoded = true) truck_id: String?
    ): TruckDetailsResponseModel


    @FormUrlEncoded
    @POST("fleet/posted_trucks")
    suspend fun getTruckList(
        @Field("user_master_id", encoded = true) userId: String?
    ): TruckListResponseModel


    @FormUrlEncoded
    @POST("fleet/update_truck")
    suspend fun updateTruckDetails(
        @Field("truck_id", encoded = true) truck_id: String?,
        @Field("user_master_id", encoded = true) user_master_id: String?,
        @Field("truck_vin_number", encoded = true) truck_vin_number: String?,
        @Field("truck_reg_number", encoded = true) truck_reg_number: String?,
        @Field("truck_make", encoded = true) truck_make: String?,
        @Field("truck_body_style", encoded = true) truck_body_style: String?,
        @Field("truck_model", encoded = true) truck_model: String?,
        @Field("truck_color", encoded = true) truck_color: String?,
        @Field("truck_cabin_type", encoded = true) truck_cabin_type: String?,
        @Field("truck_empty_weight", encoded = true) truck_empty_weight: String?,
        @Field("truck_carring_capacity", encoded = true) truck_carring_capacity: String?,
        @Field("truck_weight_metric", encoded = true) truck_weight_metric: String?,
        @Field("truck_type", encoded = true) truck_type: String?,
        @Field("company_trucknumber", encoded = true) company_trucknumber: String?
    ): EditTruckDetailsResponseModel


    @FormUrlEncoded
    @POST("fleet/posted_trucks")
    suspend fun getTruckTypeList(
        @Field("user_master_id", encoded = true) userId: String?,
        @Field("truck_type", encoded = true) truckType: Int?
    ): TruckListResponseModel


    @POST("home/getStateList")
    suspend fun getStateList(
    ): StateListResponseModel


    @FormUrlEncoded
    @POST("home/getCityList")
    suspend fun getCityList(
        @Field("state_id", encoded = true) state_id: String?,
    ): CityListResponseModel



    @FormUrlEncoded
    @POST("user/submit_register")
    suspend fun registration(
        @Field("mobile", encoded = true) mobile: String?,
        @Field("registration_source", encoded = true) registration_source: String?,
        @Field("company_registration", encoded = true) company_registration: String?,
        @Field("fname", encoded = true) fname: String?,
        @Field("state", encoded = true) state: String?,
        @Field("email", encoded = true) email: String?,
        @Field("lanme", encoded = true) lanme: String?,
        @Field("password", encoded = true) password: String?,
        @Field("user_role", encoded = true) user_role: String?,
        @Field("company_name", encoded = true) company_name: String?,
        @Field("city", encoded = true) city: String?,
        @Field("latitude", encoded = true) latitude: String?,
        @Field("longitude", encoded = true) longitude: String?
    ): RegistrationResponseModel



    @FormUrlEncoded
    @POST("fleet/delete_truck")
    suspend fun deleteTruck(
        @Field("truck_id", encoded = true) truck_id: String?,
    ): DelteTruckResponseModel



    @FormUrlEncoded
    @POST("user/deviceLogout")
    suspend fun logout(
        @Field("user_id", encoded = true) user_id: String?,
    ): LogoutModelResponse



    @GET("admin/appintroController/appIntroList")
    suspend fun getappIntroList(
    ): AppIntroResponseModel





}