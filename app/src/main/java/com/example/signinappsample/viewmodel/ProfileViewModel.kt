package com.example.signinappsample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.signinappsample.api.MainRepository
import com.example.signinappsample.util.Resource
import kotlinx.coroutines.Dispatchers

class ProfileViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}