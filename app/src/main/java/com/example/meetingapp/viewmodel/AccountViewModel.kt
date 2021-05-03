package com.example.meetingapp.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.meetingapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class AccountViewModel : ViewModel() {


    fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("account", Context.MODE_PRIVATE)
    }
    fun getUser(auth: FirebaseAuth) :User?{
        var user : User? = null
        var database = FirebaseFirestore.getInstance()
        var data = database.collection("users")
            .document(auth.currentUser!!.uid)
        data.get()
            .addOnCompleteListener{ task ->
                if(task.isSuccessful && task.result != null){
                    var gson = Gson()
                    user= gson.fromJson(task.result!!.data.toString(),User::class.java)
                    Log.d("BBB", "name : ${user!!.name}\nemail : ${user!!.email}\npass : ${user!!.password}")
                }
            }
        return user
    }
}