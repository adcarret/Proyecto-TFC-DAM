package com.example.taskshare_tfc.views.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskshare_tfc.R

@Composable

fun LoginView (){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        var email by remember { mutableStateOf("") } //Variable email
        var password by remember { mutableStateOf("") } //Variable password
    
        //Titulo
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "Login de usuario",
            fontWeight = FontWeight.Bold, //Negrita
            color = Color.Black, // Color del texto
            fontSize = 20.sp //Tamaño de la fuente
            )

        //Icono de login
        Image(
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .padding(15.dp),
            painter = painterResource(id = R.drawable.password_9482401),
            contentDescription = "Icono del login"
        )

        //Campo email
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            value = email,
            onValueChange = {email = it},
            label = {Text(text = "Email")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        //Campo password
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            value = password,
            onValueChange = {password = it},
            label = {Text(text = "Password")},
            //esta linea no mostrará sugerencias cuando estemos ingresando la contraseña
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(20.dp))

        //Botón de login
        Button(
            onClick = {/*TODO*/},
            modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)){
            Text(text = "Ingresar")
        }

        //Botón de registro
        Button(
            onClick = {/*TODO*/},
            modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)){
            Text(text = "Registrarme")
        }
    }
}