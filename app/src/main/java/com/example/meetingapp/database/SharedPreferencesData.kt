package com.example.meetingapp.database

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.meetingapp.model.User

@SuppressLint("CommitPrefEdits")
class SharedPreferencesData(context: Context) {

    private var sharedPreferences : SharedPreferences? = null
    private  var editor : SharedPreferences.Editor? = null
    val USER_NAME = "name"
    val USER_EMAIL = "email"
    val USER_PASSWORD = "password"
    val USER_ID = "id"
    val IS_SIGNED = "is_signed"

    var user : MutableLiveData<User> = MutableLiveData<User>()

 init {
     sharedPreferences  = context.getSharedPreferences("account", MODE_PRIVATE)
     editor = sharedPreferences!!.edit()
 }


    fun checkSigned():Boolean{
        return sharedPreferences?.getBoolean(IS_SIGNED,false)!!
    }

    fun setUser(u : User){
        user.value = u
        Log.d("BBB", "name : ${user.value!!.name}\nemail : ${user.value!!.email}\npass : ${user.value!!.password}\nid : ${user.value!!.id}")
        editor!!.putString(USER_NAME,user.value!!.name)
        editor!!.putString(USER_EMAIL,user.value!!.email)
        editor!!.putString(USER_PASSWORD,user.value!!.password)
        editor!!.putString(USER_ID,user.value!!.id)
        editor!!.putBoolean(IS_SIGNED,true)
        editor!!.commit()

    }
    @JvmName("getUser1")
    fun getUser():MutableLiveData<User>{
        user.value= User(sharedPreferences?.getString(USER_NAME,"name"),
            sharedPreferences?.getString(USER_EMAIL,"email")!!,
            sharedPreferences?.getString(USER_PASSWORD,"pass")!!,
            sharedPreferences?.getString(USER_ID,"id")!!,
        )
        return user
    }

}