package com.example.taskshare_tfc.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskshare_tfc.R

@Composable

fun CardContact(
    names : String,
    email : String,
    address : String,
    phone : String,
    onClick : () -> Unit
){
    Card(
        modifier = Modifier
            .padding(10.dp),
        shape = RoundedCornerShape(
            topEnd = 10.dp,
            topStart = 10.dp,
            bottomEnd = 10.dp,
            bottomStart = 10.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color(0xF329AD78))
    ) {

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(10.dp)
        ){
            Image(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .padding(15.dp),
                painter = painterResource(id = R.drawable.ico_contacto_card),
                contentDescription = "Icono de contacto"
            )

            //Nombres
            Text(text = names, color = Color.White, fontSize = 20.sp, modifier = Modifier.fillMaxWidth())

            //Email
            Text(text = "Email: $email", color = Color.White, modifier = Modifier.fillMaxWidth())

            //Direccion
            Text(text = "Dirección: $address", color = Color.White, modifier = Modifier.fillMaxWidth())

            //Telefono
            Text(text = "Teléfono: $phone", color = Color.White, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = {onClick()}) {

                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar contacto",
                    tint = Color.White
                )
            }

        }


    }
}