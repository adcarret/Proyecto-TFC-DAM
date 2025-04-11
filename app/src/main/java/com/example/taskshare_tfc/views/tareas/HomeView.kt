package com.example.taskshare_tfc.views.tareas

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.taskshare_tfc.viewModels.TareasViewModel

@Composable
fun HomeView(navController: NavController, notesVM : TareasViewModel){
    Text(text = "Inicio")
}