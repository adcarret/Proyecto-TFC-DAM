package com.example.taskshare_tfc.models
    //El password no lo almaceno
    /*
    Esta es la info que almacenaré
    en la BBDD. id, email y username
     */
    data class UserModel(
    val userId : String,
    val email : String,
    val username : String
){

}
