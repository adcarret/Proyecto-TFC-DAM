package com.example.taskshare_tfc.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskshare_tfc.models.TaskModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class TareasViewModel : ViewModel() {

    private val auth : FirebaseAuth = Firebase.auth //Autenticación
    private val firestore = Firebase.firestore //BBDD

    private val _taskData = MutableStateFlow<List<TaskModel>>(emptyList())
    val taskData : StateFlow<List<TaskModel>> = _taskData

    var state by mutableStateOf(TaskModel())
        private set

    fun onValue(value : String, text : String){
        when(text){
            "title" -> state = state.copy(title = value)
            "description" -> state = state.copy(description = value)
        }
    }


    fun saveTask(title : String, description : String,  selectedDate: String, selectedTime: String, onSuccess : () -> Unit){
        val email = auth.currentUser?.email //Obtengo el correo del user

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val tarea = hashMapOf(
                    "title" to title,
                    "description" to description,
                    "date" to "$selectedDate $selectedTime",
                    "email" to email.toString().trim()
                )

                firestore.collection("Tasks").add(tarea)
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener { e ->
                        Log.d("Error", "${e.message}")
                    }
            }catch (e : Exception){
                Log.d("Error", "No se pudo registrar la tarea")
            }

        }
    }

    fun getTasks(){
        val email = auth.currentUser?.email //obtengo el correo del usuario

        firestore.collection("Tasks")//Nombre de la bbdd en firebase
            .whereEqualTo("email", email.toString())
            //Ordeno las notas por fecha en orden descendente
            .orderBy("date", Query.Direction.DESCENDING)
            .addSnapshotListener { query, error ->
                if(error != null){
                    return@addSnapshotListener
                }
                val tasks = mutableListOf<TaskModel>()
                if(query != null){
                    for(task in query){
                        val myTask = task.toObject(TaskModel::class.java)
                            .copy(idTask = task.id)
                        tasks.add(myTask)
                    }
                }
                _taskData.value = tasks
            }
    }

    fun getTaskId(idTask : String){
        firestore.collection("Tasks")
            .document(idTask)
            .addSnapshotListener{snapshot, error ->
                if(snapshot != null){
                    val task = snapshot.toObject(TaskModel::class.java)
                    state = state.copy(
                        title = task?.title?: "",
                        description = task?.description?: "",
                        date = task?.date ?: ""
                    )
                }
            }
    }

    fun updateDate(date: String) {
        state = state.copy(date = date)
    }

    fun editTask(idTask : String, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val editTask = hashMapOf(
                    "title" to state.title,
                    "description" to state.description,
                    "date" to state.date
                )

                firestore.collection("Tasks").document(idTask)
                    .update(editTask as Map<String, Any>)
                    .addOnSuccessListener {
                        onSuccess()
                    }.addOnFailureListener{e->
                        Log.d("Error", "${e.message}")
                    }
            }catch (e:Exception){
                Log.d("Error al editar", "${e.message}")
            }
        }
    }

    fun deleteTask(idTask: String, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            try {
                firestore.collection("Tasks").document(idTask)
                    .delete()
                    .addOnSuccessListener {
                        getTaskId(idTask)//añado esto. Si falla se puede borrar
                        onSuccess()
                    }
                    .addOnFailureListener{e->
                        Log.d("Error al eliminar", "${e.message}")
                    }
            }catch (e:Exception){
                Log.d("Error al eliminar", "${e.message}")
            }
        }
    }


    private fun formatDate() : String{
        val currentDate : Date = Calendar.getInstance().time
        val formatDate = SimpleDateFormat("dd/MM/yyyy hh:mm:a", Locale.getDefault())

        return formatDate.format(currentDate)
    }

    fun logOut(){
        auth.signOut()//Nos permite cerrar la sesión
    }
}