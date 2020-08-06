package com.example.signinappsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.signinappsample.util.InputValidator

class SignInViewModal : ViewModel() {

    private val _isEmailValidObserver = MutableLiveData<Boolean>()
    val isEmailValidObserver: LiveData<Boolean>
        get() = _isEmailValidObserver

    private val _isPasswordValidObserver = MutableLiveData<Boolean>()
    val isPasswordValidObserver: LiveData<Boolean>
        get() = _isPasswordValidObserver

    fun updateEmail(field: String, inputValidator: InputValidator.Validate) {
        _isEmailValidObserver.postValue(
            InputValidator.isValidEmail(
                field
            )
        )
    }

    fun updatePassword(field: String, inputValidator: InputValidator.Validate) {
        _isPasswordValidObserver.postValue(
            InputValidator.isValidPassword(
                field
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
    }
}