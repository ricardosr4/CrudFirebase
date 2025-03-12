package com.example.crudfirebase.ui.register.viewModel

import androidx.lifecycle.ViewModel
import com.example.crudfirebase.ui.register.registerState.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel: ViewModel() {

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

    fun onEmailChange(email: String){
        _registerState.value = _registerState.value.copy(email = email)
    }

    fun onPasswordChange(password: String){
        _registerState.value = _registerState.value.copy(password = password)
    }

    fun onConfirmPasswordChange(confirmPassword: String){
        _registerState.value = _registerState.value.copy(confirmPassword = confirmPassword)
    }

}