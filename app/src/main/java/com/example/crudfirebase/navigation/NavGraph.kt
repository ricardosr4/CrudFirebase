package com.example.crudfirebase.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crudfirebase.ui.home.screen.HomeScreen
import com.example.crudfirebase.ui.home.viewModel.HomeViewModel
import com.example.crudfirebase.ui.login.screen.BlankScreen
import com.example.crudfirebase.ui.login.screen.LoginScreen
import com.example.crudfirebase.ui.login.viewModel.LoginViewModel
import com.example.crudfirebase.ui.register.registerScreen.RegisterScreen
import com.example.crudfirebase.ui.register.viewModel.RegisterViewModel

@Composable
fun NavGraph(
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    homeViewModel: HomeViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "blank_screen") {

        composable("blank_screen") {
            BlankScreen(navController)
        }
        composable("login_screen") {
            LoginScreen(navController, loginViewModel)
        }
        composable("register_screen") {
            RegisterScreen(navController, registerViewModel)
        }
        composable("home_screen") {
            HomeScreen(navController, homeViewModel)
        }

    }
}