package com.example.taskshare_tfc.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskshare_tfc.models.ContactModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContactsViewModel : ViewModel() {
    private val auth = Firebase.auth
    private val firestore = Firebase.firestore

    private val _contactData = MutableStateFlow<List<ContactModel>>(emptyList())
    val contactData : StateFlow<List<ContactModel>> = _contactData

    var state by mutableStateOf(ContactModel())

    private set

    fun onValue(value : String , text : String){
        when(text){
            "names" -> state = state.copy(names = value)
            "email" -> state = state.copy(email = value)
            "address" -> state = state.copy(address = value)
            "phone" -> state = state.copy(phone = value)

        }
    }

    fun saveContact(
        names : String,
        email : String,
        address : String,
        phone : String,
        onSuccess : ()->Unit
    ){
        val myEmail = auth.currentUser?.email //Obteniendo el email

        viewModelScope.launch (Dispatchers.IO){

            try {
                val contact = hashMapOf(
                    "myEmail" to myEmail.toString().trim(),
                    "names" to names,
                    "email" to email,
                    "address" to address,
                    "phone" to phone
                )

                firestore.collection("Contacts").add(contact)
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener{e ->
                        Log.d("Error al registrar contacto", "No se registró")
                    }

            }catch (e:Exception){
                Log.d("Error al registrar contacto", "No se registró")
            }
        }




    }

    fun getContacts(){
        val myEmail = auth.currentUser?.email

        firestore.collection("Contacts")
            .whereEqualTo("myEmail", myEmail.toString())
            .orderBy("names", Query.Direction.ASCENDING)
            .addSnapshotListener{query, error ->

                if(error != null){
                    return@addSnapshotListener
                }

                val contacts = mutableListOf<ContactModel>()

                if(query != null){
                    for (contact in query){
                        val myContacts = contact.toObject(ContactModel::class.java)
                            .copy(idContact = contact.id)
                        contacts.add(myContacts)
                    }
                }
                _contactData.value = contacts
            }
    }


    fun getContactById(idContact : String){
        firestore.collection("Contacts")
            .document(idContact)
            .addSnapshotListener{snapShot, error ->
                if(snapShot != null){
                    val contact = snapShot.toObject(ContactModel::class.java)
                    state = state.copy(
                        names = contact?.names?: "",
                        email = contact?.email?: "",
                        address = contact?.address?: "",
                        phone = contact?.phone?: ""
                    )
                }
            }
    }
}