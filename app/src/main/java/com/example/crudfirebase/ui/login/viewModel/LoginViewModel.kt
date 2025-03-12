package com.example.crudfirebase.ui.login.viewModel

import androidx.lifecycle.ViewModel
import com.example.crudfirebase.ui.login.loginState.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel:ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun onEmailChange(email: String){
        _loginState.value = _loginState.value.copy(email = email)

    }
    fun onPasswordChange(password :String){
        _loginState.value = _loginState.value.copy(password = password)
    }


}