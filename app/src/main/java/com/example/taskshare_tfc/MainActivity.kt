package com.example.taskshare_tfc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.example.taskshare_tfc.navigation.NavManager
import com.example.taskshare_tfc.ui.theme.TaskShareTFCTheme
import com.example.taskshare_tfc.viewModels.ContactsViewModel
import com.example.taskshare_tfc.viewModels.LoginViewModel
import com.example.taskshare_tfc.viewModels.RegisterViewModel
import com.example.taskshare_tfc.viewModels.TareasViewModel
import com.example.taskshare_tfc.views.login.LoginView
/**
 * Autor: Adrián Carretero Alcázar
 * Fecha de creación: 6 Abril 2025
 * Versión: 1.0
 * Descripción: Este archivo contiene el main de la App.
 */
class MainActivity : ComponentActivity() {

    val loginVM : LoginViewModel by viewModels()
    val registerVM : RegisterViewModel by viewModels()
    val notesVM : TareasViewModel by viewModels()
    val contactVM : ContactsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            TaskShareTFCTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavManager(loginVM, registerVM, notesVM, contactVM)
                }
            }
        }
    }
}

/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskShareTFCTheme {
        Greeting("Android")
    }
}
*/
