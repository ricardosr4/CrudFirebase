package com.example.crudfirebase.ui.register.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudfirebase.data.model.UsersModel
import com.example.crudfirebase.ui.register.registerState.RegisterState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

    fun onEmailChange(email: String) {
        _registerState.value = _registerState.value.copy(email = email)
    }

    fun onNameChange(name: String) {
        _registerState.value = _registerState.value.copy(name = name)
    }

    fun onPasswordChange(password: String) {
        _registerState.value = _registerState.value.copy(password = password)
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _registerState.value = _registerState.value.copy(confirmPassword = confirmPassword)
    }

    private fun cleanFields() {
        _registerState.value = RegisterState()
    }

    fun closeAlert() {
        _registerState.value = registerState.value.copy(showAlert = false)
    }

    private fun validateFields(
        email: String,
        name: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        return email.isNotEmpty() &&
                name.isNotEmpty() &&
                password.isNotEmpty() &&
                confirmPassword.isNotEmpty() &&
                password == confirmPassword
    }


    fun createUser(
        email: String,
        userName: String,
        password: String,
        confirmPassword: String,
        onSuccess: () -> Unit
    ) {
        if (validateFields(email, userName, password, confirmPassword)) {
            viewModelScope.launch {
                try {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                saveUser(userName)
                                onSuccess()
                                cleanFields()
                            } else {
                                Log.d("ERROR EN FIREBASE", "Usuario y contraseña incorrectos")
                                _registerState.value = registerState.value.copy(showAlert = true)
                            }
                        }
                } catch (e: Exception) {
                    Log.d("ERROR EN FIREBASE", "Error: ${e.localizedMessage}")
                }
            }
        } else {
            Log.d("VALIDACION", "Los campos no son válidos o la contraseña no coincide.")
            _registerState.value = registerState.value.copy(showAlert = true)
        }
    }

    //esta funcion es para guardar el usuario en base de datos de firebase
    private fun saveUser(userName: String) {
        val id = auth.currentUser?.uid
        val email = auth.currentUser?.email

        val user = UsersModel(
            userId = id.toString(),
            email = email.toString(),
            username = userName
        )

        FirebaseFirestore.getInstance().collection("Users")
            .add(user)
            .addOnSuccessListener {
                Log.d("FIREBASE", "Se guardo el usuario")
            }.addOnFailureListener {
                Log.d("FIREBASE", "No se pudo guardar el usuario")
            }
    }
}