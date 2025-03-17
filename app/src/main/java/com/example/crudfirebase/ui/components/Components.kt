package com.example.crudfirebase.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ZetaSpaceHeight(size: Dp = 10.dp) {
    Spacer(modifier = Modifier.height(size))
}

@Composable
fun ZetaSpaceWidth(size: Dp = 10.dp) {
    Spacer(modifier = Modifier.width(size))
}

@Composable
fun ZetaOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Email,
    visualTransformation: VisualTransformation = VisualTransformation.None

) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    )

}

@Composable
fun ZetaButtonBasic(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colorScheme.primary,
    textSize: TextUnit = 16.sp,

    ) {
    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(1.dp, color),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = color,
            containerColor = Color.Transparent,

            ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)

    ) {
        Text(text = text, fontSize = textSize)
    }
}

@Composable
fun ZetaAlertDialog1(
    title: String,
    message: String,
    confirmText: String,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissClick,
        title = { Text(text = title) },
        text = { Text(text = message) },
        confirmButton = {
            OutlinedButton(onClick = onConfirmClick) {
                Text(text = confirmText)
            }
        }
    )

}

@Composable
fun ZetaText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    fontSize: TextUnit = 16.sp,

    ) {
    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        modifier = modifier
    )
}

@Composable
fun ZetaAlertDialog(
    title: String,
    message: String,
    confirmText: String,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    val scroll = rememberScrollState(0)

    AlertDialog(
        onDismissRequest = { onDismissClick() },
        title = { Text(text = title) },
        text = {
            Text(
                text = message,
                textAlign = TextAlign.Justify,
                modifier = Modifier.verticalScroll(scroll)
            )
        },
        confirmButton = {
            Button(onClick = { onConfirmClick() }) {
                Text(text = confirmText)
            }
        }
    )
}

@Composable
fun ZetaCardNote(
    title: String,
    note: String,
    date: String,
    onClick: () -> Unit
) {
    var showAlert by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 10.dp)
            .clickable { showAlert = true }

    ) {
        Row {
            Column {
                Text(text = title, fontWeight = FontWeight.Bold)
                Text(text = date, color = Color.Gray)

            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { onClick() }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "")
            }
        }
        Divider()
        if (showAlert) {
            ZetaAlertDialog(
                title = title,
                message = note,
                confirmText = "Aceptar",
                onConfirmClick = { showAlert = false }) {

            }
        }
    }
}


