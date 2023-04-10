package com.app.conectar.api.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.conectar.api.repository.MainRepository
import com.app.conectar.model.edittruck_details.EditTruckDetailsRequestModel
import com.app.conectar.retrofit.Resource
import kotlinx.coroutines.Dispatchers

class CommonViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun login(
        email: String,
        password: String,
        source: String,
        device_token: String,
        device_type: String
    ) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        try {
            emit(
                Resource.success(
                    data = mainRepository.login(
                        email,
                        password,
                        source,
                        device_token,
                        device_type
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }


    fun loginwithphone(
        country_code: String,
        mobile: String,
        source: String,
        device_token: String,
        device_type: String
    ) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        try {
            emit(
                Resource.success(
                    data = mainRepository.loginwithphone(
                        country_code,
                        mobile,
                        source,
                        device_token,
                        device_type
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }


    fun getUserProfileDetails(userId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUserProfileDetails(userId)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }


    fun resendotp(user_master_id: String, email: String, mobile: String) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        data = mainRepository.resendotp(
                            user_master_id,
                            email,
                            mobile
                        )
                    )
                )
            } catch (e: Exception) {
                emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
            }
        }


    fun verifyuser(user_master_id: String, otp: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.verifyuser(user_master_id, otp)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }


    fun fleetnewjoblist(user_master_id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.fleetnewjoblist(user_master_id)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }


    fun myjob(user_master_id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.myjob(user_master_id)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }


    fun notificationlist(user_master_id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.notificationlist(user_master_id)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }


    fun getPasswordResetUpdate(password: String, password_token: String) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        data = mainRepository.getPasswordResetUpdate(
                            password,
                            password_token
                        )
                    )
                )
            } catch (e: Exception) {
                emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
            }
        }


    fun getForgetPassword(emailId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getForgetPassword(emailId)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }


    fun getDriverList(userId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getDriverList(userId)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }


    fun getTruckDetails(truckId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getTruckDetails(truckId)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }


    fun getTruckList(userId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getTruckList(userId)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }


    fun getCityList(state_id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getCityList(state_id)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }


    fun deleteTruck(truck_id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.deleteTruck(truck_id)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }




    fun logout(user_id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.logout(user_id)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }




    fun registration(mobile:String, registration_source:String, company_registration:String,
                     fname:String, state:String, email: String, lanme: String, password: String,
                     user_role: String, company_name: String, city: String, latitude:String, longitude:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.registration(mobile, registration_source, company_registration, fname, state, email, lanme,
                password, user_role, company_name, city, latitude, longitude)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }



    fun getStateList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getStateList()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }


    fun getappIntroList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getappIntroList()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }


    fun updateTruckDetails(editTruckDetailsRequestModel: EditTruckDetailsRequestModel) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        data = mainRepository.updateTruckDetails(
                            editTruckDetailsRequestModel
                        )
                    )
                )
            } catch (e: Exception) {
                emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
            }
        }


}