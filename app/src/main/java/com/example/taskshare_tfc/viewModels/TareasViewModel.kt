package com.example.taskshare_tfc.viewModels

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class TareasViewModel : ViewModel() {
    private val auth : FirebaseAuth = Firebase.auth

    fun logOut(){
        auth.signOut()//Nos permite cerrar la sesión
    }
}