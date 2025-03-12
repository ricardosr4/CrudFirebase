package com.example.crudfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.crudfirebase.ui.login.screen.LoginScreen
import com.example.crudfirebase.ui.login.viewModel.LoginViewModel
import com.example.crudfirebase.ui.theme.CrudFirebaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = LoginViewModel()

        setContent {
            CrudFirebaseTheme {
                LoginScreen(viewModel)


            }
        }
    }
}

