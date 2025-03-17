package com.example.crudfirebase.ui.notes.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.crudfirebase.ui.components.ZetaSpaceHeight
import com.example.crudfirebase.ui.home.viewModel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNotesScreen(navController: NavController, viewModel: HomeViewModel, idDoc: String) {

    LaunchedEffect(Unit) {
        viewModel.getNotById(idDoc)
    }
    val context = LocalContext.current
    val state = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Editar Nota") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },


                actions = {
                    IconButton(onClick ={
                        viewModel.deleteNote(idDoc){
                            Toast.makeText(context, "Nota eliminada", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }
                    } ) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                    }
                    IconButton(onClick = {
                        viewModel.updateNote(idDoc) {
                            Toast.makeText(context, "Nota actualizada", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }

                    }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Back")
                    }

                }
            )
        }
    ) { pad ->
        Column(
            modifier = Modifier
                .padding(pad)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            OutlinedTextField(
                value = state.title,
                onValueChange = { viewModel.onValue(it, "title") },
                label = { Text(text = "Titulo") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            )
            ZetaSpaceHeight()
            OutlinedTextField(
                value = state.note,
                onValueChange = { viewModel.onValue(it, "note") },
                label = { Text(text = "Nota") },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )
        }
    }


}


//Text(text = state.title)