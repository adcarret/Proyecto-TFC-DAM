package com.example.taskshare_tfc.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskshare_tfc.models.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    //Con esto podre usar los metodos de autenticacion
    private val auth : FirebaseAuth = Firebase.auth

    /*
    onSucces : () -> Unit Nos permitirá ejecutar
    una accion cuando estemos desde el
    apartado grafico
     */
    fun createUser(username : String, email : String, password : String, onSucces : () -> Unit){
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            saveUser(username)
                            onSucces()
                        }else{
                            Log.d("Error", "Error al crear cuenta")
                        }
                    }
            }catch (e : Exception){
                Log.d("Error", "${e.message}")
            }
        }
    }

    fun saveUser(username : String){
        val id = auth.currentUser?.uid
        val email = auth.currentUser?.email

        if (id != null && email != null) {
            val user = UserModel(
                userId = id,
                email = email,
                username = username
            )

            viewModelScope.launch(Dispatchers.IO) {
                FirebaseFirestore.getInstance().collection("Users")
                    .document(id!!) // Usamos el UID como ID del documento
                    .set(user)
                    .addOnSuccessListener {
                        Log.d("Succes", "Se guardó la información del usuario")
                    }
                    .addOnFailureListener { e ->
                        Log.d("Error", "${e.message}")
                    }
            }
        } else {
            Log.d("Error", "auth.currentUser es null")
        }
    }



}







