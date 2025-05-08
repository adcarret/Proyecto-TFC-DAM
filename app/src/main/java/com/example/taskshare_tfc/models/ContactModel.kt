package com.example.taskshare_tfc.models

data class ContactModel(
    val myEmail : String = "", //Correo del usuario el que realiza el registro del contacto
    val names : String = "",
    val email : String = "", //Correo del contacto
    val address : String = "",
    val phone : String = "",
    val idContact : String = ""

)
