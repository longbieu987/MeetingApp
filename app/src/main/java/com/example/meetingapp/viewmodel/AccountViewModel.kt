package com.example.meetingapp.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.meetingapp.database.SharedPreferencesData
import com.example.meetingapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.coroutines.awaitAll

class AccountViewModel : ViewModel() {
    var user : MutableLiveData<User> = MutableLiveData<User>()
     var sharedPreferences: SharedPreferencesData? = null

    fun getSharedPreferences(context: Context) : SharedPreferencesData{
        if (sharedPreferences == null){
            sharedPreferences = SharedPreferencesData(context)
        }
        return sharedPreferences!!
    }
    fun getUserFromFirebase(auth: FirebaseAuth) :MutableLiveData<User>{
        var database = FirebaseFirestore.getInstance()
        var data = database.collection("users")
            .document(auth.currentUser!!.uid)
        data.get()
            .addOnCompleteListener{ task ->
                if(task.isSuccessful && task.result != null){
                    val gson = Gson()
                    val u = gson.fromJson(task.result!!.data.toString(),User::class.java)
                    u.id =auth.currentUser!!.uid
                    user.value = u
                }
            }
        return user
    }
}