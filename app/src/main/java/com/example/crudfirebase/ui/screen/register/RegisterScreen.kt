package com.example.crudfirebase.ui.screen.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crudfirebase.utils.components.ZetaText

@Composable
fun RegisterScreen(){

    Column {
        ZetaText(
            text = "Register",
            color = Color.Black,
            fontSize = 50.sp,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Start)
                .padding(start = 20.dp, top = 50.dp)
        )
    }

}