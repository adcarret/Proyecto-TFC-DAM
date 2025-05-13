package com.example.taskshare_tfc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskshare_tfc.viewModels.ContactsViewModel
import com.example.taskshare_tfc.viewModels.LoginViewModel
import com.example.taskshare_tfc.viewModels.RegisterViewModel
import com.example.taskshare_tfc.viewModels.TareasViewModel
import com.example.taskshare_tfc.views.contacts.AddContactsView
import com.example.taskshare_tfc.views.contacts.AllContactsView
import com.example.taskshare_tfc.views.contacts.EditContactView
import com.example.taskshare_tfc.views.login.CheckSessionView
import com.example.taskshare_tfc.views.login.LoginView
import com.example.taskshare_tfc.views.register.RegisterView
import com.example.taskshare_tfc.views.tareas.AddTaskView
import com.example.taskshare_tfc.views.tareas.EditTaskView
import com.example.taskshare_tfc.views.tareas.HomeView
import com.example.taskshare_tfc.views.tareas.allTasksView

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
    notesVM : TareasViewModel,
    contactsVM: ContactsViewModel
){
    val navController = rememberNavController()

    //Cuando se inicie la app mostraremos por defecto la pantalla del Login
    NavHost(navController = navController, startDestination = "CheckSession" ){

        composable("CheckSession"){
            CheckSessionView(navController)
        }
        composable("Login"){
            LoginView(navController, loginVM)
        }

        composable("Register"){
            RegisterView(navController, registerVM)
        }

        composable("Home"){
            HomeView(navController, notesVM)
        }

        composable("AddTask"){
            AddTaskView(navController, notesVM)
        }

        composable("AllTasks"){
            allTasksView(navController, notesVM)
        }

        composable("EditTask/{idTask}", arguments = listOf(
            navArgument("idTask"){
                type = NavType.StringType
            }
        )){
            val idTask = it.arguments?.getString("idTask")?: ""
            EditTaskView(navController, notesVM, idTask)
        }

        composable("AddContact"){
            AddContactsView(navController, contactsVM)
        }

        composable("AllContacts"){
            AllContactsView(navController, contactsVM)
        }

        composable("EditContact/{idContact}", arguments = listOf(
            navArgument("idContact"){type = NavType.StringType}
        )){
            val idContact = it.arguments?.getString("idContact")?:""
            EditContactView(navController, contactsVM, idContact)
        }
    }
}