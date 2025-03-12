package com.example.crudfirebase.ui.login.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.crudfirebase.R
import com.example.crudfirebase.ui.login.viewModel.LoginViewModel
import com.example.crudfirebase.utils.components.ZetaButtonBasic
import com.example.crudfirebase.utils.components.ZetaOutlinedTextField
import com.example.crudfirebase.utils.components.ZetaSpaceHeight
import com.example.crudfirebase.utils.components.ZetaText

@Composable
fun LoginScreen(viewModel: LoginViewModel){

    val state by viewModel.loginState.collectAsState()



    Column {
        ZetaText(
            text = stringResource(R.string.login),
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
            onValueChange = { viewModel.onPasswordChange(it)},
            label = stringResource(R.string.password),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()

        )
        ZetaSpaceHeight(100.dp)
        ZetaButtonBasic(
            text = stringResource(R.string.login),
            color = Color.Black,
            textSize = 20.sp,
            onClick = { /*TODO*/ }
        )


    }
}