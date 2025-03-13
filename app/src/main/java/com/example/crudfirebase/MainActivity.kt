package com.example.crudfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.crudfirebase.navigation.NavGraph
import com.example.crudfirebase.ui.login.viewModel.LoginViewModel
import com.example.crudfirebase.ui.register.viewModel.RegisterViewModel
import com.example.crudfirebase.ui.theme.CrudFirebaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginViewModel : LoginViewModel by viewModels()
        val registerViewModel : RegisterViewModel by viewModels()

        setContent {
            CrudFirebaseTheme {
               NavGraph(loginViewModel,
                   registerViewModel)


            }
        }
    }
}

