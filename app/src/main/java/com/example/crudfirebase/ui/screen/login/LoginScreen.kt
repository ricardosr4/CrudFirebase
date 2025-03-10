package com.example.crudfirebase.ui.screen.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crudfirebase.utils.components.ZetaOutlinedTextField
import com.example.crudfirebase.utils.components.ZetaSpaceHeight
import com.example.crudfirebase.utils.components.ZetaText

@Composable
fun LoginScreen(){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column {
        ZetaText(
            text = "Login",
            color = Color.Black,
            fontSize = 50.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(start = 30.dp, top = 50.dp)
        )
        ZetaSpaceHeight(30.dp)
        ZetaOutlinedTextField(
            value = email,
            onValueChange = { email = it},
            label = "Email",
            keyboardType = KeyboardType.Email

        )
        ZetaSpaceHeight(20.dp)
        ZetaOutlinedTextField(
            value = password,
            onValueChange = { password = it},
            label = "Password",
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()

        )


    }
}