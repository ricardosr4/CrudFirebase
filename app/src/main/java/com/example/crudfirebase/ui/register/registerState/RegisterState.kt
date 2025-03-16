package com.example.crudfirebase.ui.register.registerState


data class RegisterState(
    val email: String = "",
    val name: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val showAlert: Boolean = false
)
