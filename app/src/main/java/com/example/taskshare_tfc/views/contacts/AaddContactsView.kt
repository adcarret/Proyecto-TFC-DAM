package com.example.taskshare_tfc.views.contacts

import androidx.compose.animation.core.rememberTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.taskshare_tfc.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun AddContactsView(){
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Agregar contacto") },
            navigationIcon = {
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "")
                }
            })
    })
    {paddingValues ->
        Column(modifier = Modifier.
            padding(paddingValues)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            var names by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var address by remember { mutableStateOf("") }
            var phone by remember { mutableStateOf("") }

            Image(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .padding(15.dp),
                painter = painterResource(id = R.drawable.agregarusuario), contentDescription = "Icono agregar contacto"
            )
            /*Campo para los nombres*/
            OutlinedTextField(
                value = names,
                onValueChange = {names = it},
                label = { Text(text = "Nombres") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
            )

            /*Campo para email*/
            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
            )

            /*Campo para direccion*/
            OutlinedTextField(
                value = address,
                onValueChange = {address = it},
                label = { Text(text = "Dirección") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
            )

            /*Campo para telefono*/
            OutlinedTextField(
                value = phone,
                onValueChange = {phone = it},
                label = { Text(text = "Teléfono") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
            )

            /*Botón para agregar un contacto*/
            Button(
                onClick ={

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)) {
                Text(text = "Agregar contacto")

            }


        }
    }
}