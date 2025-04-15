package com.example.taskshare_tfc.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class TareasViewModel : ViewModel() {

    private val auth : FirebaseAuth = Firebase.auth //Autenticación
    private val firestore = Firebase.firestore //BBDD


    fun saveTask(title : String, description : String,  selectedDate: String, selectedTime: String, onSuccess : () -> Unit){
        val email = auth.currentUser?.email //Obtengo el correo del user

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val tarea = hashMapOf(
                    "title" to title,
                    "description" to description,
                    "date" to "$selectedDate $selectedTime",
                    "email" to email.toString().trim()
                )

                firestore.collection("Tasks").add(tarea)
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener { e ->
                        Log.d("Error", "${e.message}")
                    }
            }catch (e : Exception){
                Log.d("Error", "No se pudo registrar la tarea")
            }

        }
    }

    private fun formatDate() : String{
        val currentDate : Date = Calendar.getInstance().time
        val formatDate = SimpleDateFormat("dd/MM/yyyy hh:mm:a", Locale.getDefault())

        return formatDate.format(currentDate)
    }

    fun logOut(){
        auth.signOut()//Nos permite cerrar la sesión
    }
}