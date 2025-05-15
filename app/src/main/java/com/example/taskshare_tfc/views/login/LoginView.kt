package com.example.taskshare_tfc.views.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
import com.airbnb.lottie.compose.*

@Composable
fun LoginView(navController: NavController, loginViewModel: LoginViewModel) {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    // Lottie animation setup
    // Lottie animation setup
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("AnimacionFondoLogin.json"))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )


    Box(modifier = Modifier.fillMaxSize()) {

        // 🎞 Fondo animado Lottie (marca de agua)
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = 1.50f) // Transparencia baja estilo marca de agua
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "Login de usuario",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp
            )

            Image(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .padding(15.dp),
                painter = painterResource(id = R.drawable.password_9482401),
                contentDescription = "Icono del login"
            )

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = ""
                },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            )

            if (emailError.isNotBlank()) {
                Text(
                    text = emailError,
                    color = Color.Red,
                    modifier = Modifier.padding(horizontal = 30.dp)
                )
            }

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = ""
                },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            )

            if (passwordError.isNotBlank()) {
                Text(
                    text = passwordError,
                    color = Color.Red,
                    modifier = Modifier.padding(horizontal = 30.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    var isValid = true
                    if (email.isBlank()) {
                        emailError = "El email no puede estar vacío."
                        isValid = false
                    } else if (!email.contains("@")) {
                        emailError = "Introduce un email válido."
                        isValid = false
                    }

                    if (password.isBlank()) {
                        passwordError = "La contraseña no puede estar vacía."
                        isValid = false
                    } else if (password.length < 6) {
                        passwordError = "La contraseña debe tener al menos 6 caracteres."
                        isValid = false
                    }

                    if (isValid) {
                        loginViewModel.login(email, password) {
                            navController.navigate("Home")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text("Ingresar")
            }

            Button(
                onClick = {
                    navController.navigate("Register")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text("Registrarme")
            }
        }
    }
}
