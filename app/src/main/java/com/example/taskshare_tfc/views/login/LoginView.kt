package com.example.taskshare_tfc.views.login

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskshare_tfc.R
import com.example.taskshare_tfc.viewModels.LoginViewModel

/*
Estoy utilizando Jetpack Compose
Por eso mismo sale este @
 */
@Composable
fun LoginView (navController: NavController, loginViewModel: LoginViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current

        var email by remember { mutableStateOf("") } //Variable email
        var password by remember { mutableStateOf("") } //Variable password

        var emailError by remember { mutableStateOf("") } // Mensaje de error para email
        var passwordError by remember { mutableStateOf("") } // Mensaje de error para password

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
            onValueChange =
                {
                    email = it
                    emailError = ""
                },// Limpiamos el mensaje de error cada vez que el usuario cambia el campo
            label = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        // Mostrar error si el email es inválido
        if (emailError.isNotBlank()) {
            Text(
                text = emailError,
                color = Color.Red,
                modifier = Modifier.padding(start = 30.dp, end = 30.dp)
            )
        }

        //Campo password
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            value = password,
            onValueChange =
                {
                    password = it
                    passwordError =
                        "" // Limpiamos el mensaje de error cada vez que el usuario cambia el campo
                },
            label = { Text(text = "Password") },
            //esta linea no mostrará sugerencias cuando estemos ingresando la contraseña
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        // Mostrar error si la contraseña es inválida
        if (passwordError.isNotBlank()) {
            Text(
                text = passwordError,
                color = Color.Red,
                modifier = Modifier.padding(start = 30.dp, end = 30.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Botón de login con validación independiente
        Button(
            onClick = {
                var isValid = true

                // ✅ Validación del email
                if (email.isBlank()) {
                    emailError = "El email no puede estar vacío."
                    isValid = false
                } else if (!email.contains("@")) {
                    emailError = "Introduce un email válido."
                    isValid = false
                }

                // ✅ Validación de la contraseña
                if (password.isBlank()) {
                    passwordError = "La contraseña no puede estar vacía."
                    isValid = false
                } else if (password.length < 6) {
                    passwordError = "La contraseña debe tener al menos 6 caracteres."
                    isValid = false
                }

                if (isValid) {
                    // Si todo es true, hacer login
                    loginViewModel.login(email, password) {
                        navController.navigate("Home")
                    }
                } else {
                    // Si hay errores, se mostraran los mensajes correspondientes.
                    // Toasts ya no son necesarios porque mostramos los errores visualmente.
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Text(text = "Ingresar")
        }

        // Botón de registro
        Button(
            onClick = {
                navController.navigate("Register")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Text(text = "Registrarme")
        }
    }
}