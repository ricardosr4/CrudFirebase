package com.example.crudfirebase.ui.register.registerScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.crudfirebase.R
import com.example.crudfirebase.ui.register.viewModel.RegisterViewModel
import com.example.crudfirebase.utils.components.ZetaButtonBasic
import com.example.crudfirebase.utils.components.ZetaOutlinedTextField
import com.example.crudfirebase.utils.components.ZetaSpaceHeight
import com.example.crudfirebase.utils.components.ZetaText

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel
) {

    val state by viewModel.registerState.collectAsState()


    Column {
        ZetaText(
            text = stringResource(R.string.register),
            color = Color.Black,
            fontSize = 50.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(start = 30.dp, top = 50.dp)
        )
        ZetaSpaceHeight(30.dp)
        ZetaOutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = stringResource(R.string.email),
            keyboardType = KeyboardType.Email

        )
        ZetaSpaceHeight(20.dp)
        ZetaOutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = stringResource(R.string.password),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()

        )
        ZetaSpaceHeight(20.dp)
        ZetaOutlinedTextField(
            value = state.confirmPassword,
            onValueChange = { viewModel.onConfirmPasswordChange(it) },
            label = stringResource(R.string.confirm_password),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()

        )
        ZetaSpaceHeight(30.dp)

        TextButton(
            onClick = { navController.navigate("login_screen") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(R.string.message_register), color = Color.Blue, fontSize = 16.sp)
        }
        ZetaSpaceHeight(100.dp)
        ZetaButtonBasic(
            text = stringResource(R.string.register),
            color = Color.Black,
            textSize = 20.sp,
            onClick = { viewModel.createUser(email = state.email, password = state.password ){
                navController.navigate("login_screen")
            } },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
    }

}