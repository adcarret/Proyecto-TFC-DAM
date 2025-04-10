package com.example.taskshare_tfc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskshare_tfc.viewModels.LoginViewModel
import com.example.taskshare_tfc.viewModels.RegisterViewModel
import com.example.taskshare_tfc.viewModels.TareasViewModel
import com.example.taskshare_tfc.views.login.LoginView
import com.example.taskshare_tfc.views.register.RegisterView

//Navegar entre las diferentes interfaces de nuestra app
/*
En la pantalla de iniciar sesion, cuando nos de
el ok, nos mandará a otra pantalla.
De eso se encarga este file
 */

@Composable

fun NavManager(
    loginVM: LoginViewModel,
    registerVM : RegisterViewModel, //Antes ponia LoginViewModel
    notesVM : TareasViewModel
){
    val navController = rememberNavController()

    //Cuando se inicie la app mostraremos por defecto la pantalla del Login
    NavHost(navController = navController, startDestination = "Login" ){
        composable("Login"){
            LoginView(navController, loginVM)
        }

        composable("Register"){
            RegisterView(navController, registerVM)
        }
    }
}