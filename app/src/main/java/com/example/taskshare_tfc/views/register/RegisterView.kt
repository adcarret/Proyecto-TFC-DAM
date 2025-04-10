package com.example.taskshare_tfc.views.register

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.taskshare_tfc.viewModels.RegisterViewModel

@Composable

fun RegisterView(navController: NavController, registerViewModel: RegisterViewModel){
    Text(text = "Registro de usuario")
}