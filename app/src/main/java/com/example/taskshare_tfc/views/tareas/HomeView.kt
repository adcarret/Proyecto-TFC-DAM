package com.example.taskshare_tfc.views.tareas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskshare_tfc.R
import com.example.taskshare_tfc.viewModels.TareasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, notesVM : TareasViewModel){
    Scaffold (
        topBar = {
            TopAppBar(title = { Text(text = "Inicio")},
                actions = {
                    IconButton(onClick = {
                        notesVM.logOut()
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = ""
                        )
                    }
                })
        }
    ){padding ->
        Column(modifier = Modifier.padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .padding(15.dp),
                painter = painterResource(id = R.drawable.ico_tarea),
                contentDescription = "Icono de tarea"

            )


            //Botón agregar una tarea
            Button(onClick = {
                navController.navigate("AddTask")
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)) {
                Text(text = "Agregar tarea")

            }

            //Ver mis tareas
            Button(onClick = {
                navController.navigate("AllTasks")
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)) {
                Text(text = "Ver mis tareas")

            }

            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .padding(15.dp),
                painter = painterResource(id = R.drawable.ico_contacto),
                contentDescription = "Icono de contacto"

            )

            Button(onClick = {}, modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)) {

                Text(text = "Agregar contacto")
            }

            Button(onClick = {}, modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)) {

                Text(text = "Ver mis contactos")
            }

        }
    }
}