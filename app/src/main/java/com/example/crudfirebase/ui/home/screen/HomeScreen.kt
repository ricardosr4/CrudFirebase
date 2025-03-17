package com.example.crudfirebase.ui.home.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.crudfirebase.ui.components.ZetaCardNote
import com.example.crudfirebase.ui.home.viewModel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {

    LaunchedEffect(Unit) {
        Log.d("HOME_SCREEN", "Fetching notes...")
        viewModel.fetchNotes()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "MIS NOTAS") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            viewModel.signOut()
                            navController.popBackStack()
                        }) {
                        Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("add_notes_screen") }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Back")
                    }

                }
            )
        }
    ) { pad ->
        Column(
            modifier = Modifier.padding(pad),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val data by viewModel.notesData.collectAsState()

            // Log to check if data is being passed correctly
            Log.d("HOME_SCREEN", "Notes data: ${data.size} items")

            if (data.isEmpty()) {
                Text("No hay notas para mostrar")
            } else {
                LazyColumn {
                    items(data) { item ->
                       ZetaCardNote(
                           title = item.title,
                           note = item.note,
                           date = item.date
                       ) {
                           navController.navigate("edit_notes_screen/${item.idDoc}")
                       }
                    }
                }
            }
        }
    }
}
