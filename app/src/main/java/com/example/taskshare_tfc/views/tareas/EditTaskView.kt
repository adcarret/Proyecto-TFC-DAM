package com.example.taskshare_tfc.views.tareas

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskshare_tfc.viewModels.TareasViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskView(
    navController: NavController,
    tareasViewModel: TareasViewModel,
    idTask: String
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
   // var showDialog by remember { mutableStateOf(false) }


    val state = tareasViewModel.state

    LaunchedEffect(Unit) {
        tareasViewModel.getTaskId(idTask)
    }

    LaunchedEffect(state.date) {
        if (state.date.isNotEmpty()) {
            val parts = state.date.split(" ")
            selectedDate = parts.getOrNull(0) ?: ""
            selectedTime = parts.getOrNull(1) ?: ""
        }
    }
    /*
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Confirmar eliminación") },
            text = { Text("¿Estás seguro de que deseas eliminar esta tarea?") },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    tareasViewModel.deleteTask(idTask) {
                        navController.popBackStack()
                    }
                }) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }*/

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Editar tarea") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = state.title,
                onValueChange = {tareasViewModel.onValue(it, "title") },
                label = { Text("Título") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )

            OutlinedTextField(
                value = state.description,
                onValueChange = { tareasViewModel.onValue(it, "description")},
                label = { Text("Descripción") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )

            // Botón para seleccionar fecha
            Button(
                onClick = {
                    val datePicker = DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            selectedDate = "%02d/%02d/%04d".format(dayOfMonth, month + 1, year)
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )
                    datePicker.datePicker.minDate = calendar.timeInMillis
                    datePicker.show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Text(if (selectedDate.isEmpty()) "Seleccionar fecha" else "Fecha: $selectedDate")
            }

            // Botón para seleccionar hora
            Button(
                onClick = {
                    TimePickerDialog(
                        context,
                        { _, hourOfDay, minute ->
                            selectedTime = "%02d:%02d".format(hourOfDay, minute)
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                    ).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(if (selectedTime.isEmpty()) "Seleccionar hora" else "Hora: $selectedTime")
            }

            // Botón para actualizar tarea
            Button(
                onClick = {
                    // Actualizar el campo date del state
                    tareasViewModel.updateDate("$selectedDate $selectedTime")

                    tareasViewModel.editTask(idTask){
                        navController.popBackStack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text("Actualizar tarea")
            }

            //Botón para eliminar tareas
            Button(onClick = {
                tareasViewModel.deleteTask(idTask){
                    tareasViewModel.getTaskId(idTask) // <- Forzamos la recarga
                    navController.popBackStack()
                }
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)) {
                Text(text = "Eliminar tarea")
            }

        }
    }
}
