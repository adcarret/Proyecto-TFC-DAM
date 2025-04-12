package com.example.taskshare_tfc.views.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable

fun CheckSessionView(navController: NavController){
    LaunchedEffect(Unit) {
        //Sí el usuario ha iniciado sesion...
        if(!FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
            navController.navigate("Home")

        //Sí el usuario no ha iniciado sesion que lo dirija al login
        }else{
            navController.navigate("Login")
        }
    }
}