package com.example.crudfirebase.ui.login.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudfirebase.ui.login.loginState.LoginState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun onEmailChange(email: String) {
        _loginState.value = _loginState.value.copy(email = email)

    }

    fun onPasswordChange(password: String) {
        _loginState.value = _loginState.value.copy(password = password)
    }
//funcion para entrar con usuario y contraseña.
    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            onSuccess()
                        } else {
                            Log.d("ERROR EN FIREBASE", "Usuario y contraseña incorrectos")
                        }
                    }
            } catch (e: Exception) {
                Log.d("ERROR EN FIREBASE", "Error: ${e.localizedMessage}")
            }
        }
    }
}