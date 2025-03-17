package com.example.crudfirebase.ui.home.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudfirebase.ui.notes.state.NotesState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class HomeViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val firestore = Firebase.firestore

    private val _notesData = MutableStateFlow<List<NotesState>>(emptyList())
    val notesData: StateFlow<List<NotesState>> = _notesData

    var state by mutableStateOf(NotesState())
        private set

    fun onValue(value:String,text:String){
        when(text){
            "title" -> state = state.copy(title = value)
            "note" -> state = state.copy(note = value)
        }

    }

    fun getNotById(documentId: String) {
        firestore.collection("Notes")
            .document(documentId)
            .addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    val note = snapshot.toObject(NotesState::class.java)
                    state = state.copy(
                        title = note?.title ?: "",
                        note = note?.note ?: "",

                    )
                }
            }
    }

    fun fetchNotes() {
        val email = auth.currentUser?.email
        firestore.collection("Notes")
            .whereEqualTo("email", email.toString())
            .addSnapshotListener { querySnapshot, error ->
                if (error != null) {
                    Log.e("ERROR", "Error fetching notes: ${error.message}")
                    return@addSnapshotListener
                }

                val documents = mutableListOf<NotesState>()
                if (querySnapshot != null) {
                    for (document in querySnapshot) {
                        val myDocument =
                            document.toObject(NotesState::class.java).copy(idDoc = document.id)
                        documents.add(myDocument)
                    }
                    // Log the number of notes fetched
                    Log.d("FETCH_NOTES", "Fetched ${documents.size} notes")
                }
                _notesData.value = documents
            }

    }

    fun signOut() {
        auth.signOut()
    }

    fun saveNewNote(title: String, note: String, onSuccess: () -> Unit) {
        val email = auth.currentUser?.email
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newNote = hashMapOf(
                    "title" to title,
                    "note" to note,
                    "date" to formatedDate(),
                    "email" to email.toString()

                )
                firestore.collection("Notes").add(newNote)
                    .addOnSuccessListener {
                        onSuccess()
                    }

            } catch (e: Exception) {
                Log.d("ERROR", "Error al guardar: ${e.message}")
            }

        }

    }

    private fun formatedDate(): String {
        val currentDate: Date = Calendar.getInstance().time
        val res = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return res.format(currentDate)
    }

    fun updateNote(idDoc: String, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val editNote = hashMapOf(
                    "title" to state.title,
                    "note" to state.note,


                )
                firestore.collection("Notes").document(idDoc)
                    .update(editNote as Map<String, Any>)
                    .addOnSuccessListener {
                        onSuccess()
                    }

            } catch (e: Exception) {
                Log.d("ERROR EDIT", "Error al editar: ${e.message}")
            }

        }

    }
    fun deleteNote(idDoc: String, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                firestore.collection("Notes").document(idDoc)
                    .delete()
                    .addOnSuccessListener {
                        onSuccess()
                    }

            } catch (e: Exception) {
                Log.d("ERROR DELETE", "Error al eliminar nota: ${e.message}")
            }

        }

    }


}