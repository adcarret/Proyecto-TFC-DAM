package com.example.taskshare_tfc.views.tareas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.taskshare_tfc.components.cardTask
import com.example.taskshare_tfc.viewModels.TareasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun allTasksView(navController: NavController, tareasViewModel: TareasViewModel){
    LaunchedEffect(Unit) {
        tareasViewModel.getTasks()
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Mis tareas") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector =  Icons.AutoMirrored.Filled.ArrowBack , //Icono de vuelta atras
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ){padding ->
        Column(
            modifier = Modifier.padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val task by tareasViewModel.taskData.collectAsState()

            LazyColumn {
                items(task){item ->
                    cardTask(
                        title = item.title,
                        description = item.description,
                        date = item.date) { }
                }
            }
        }

    }
}