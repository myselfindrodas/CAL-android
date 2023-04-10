package com.app.conectar.api.modelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.conectar.api.repository.MainRepository
import com.app.conectar.api.viewmodel.CommonViewModel
import com.app.conectar.retrofit.ApiHelper

class CommonModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        CommonViewModel(MainRepository(apiHelper)) as T
}