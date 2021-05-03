package com.example.meetingapp.database

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPreferencesData {
    private var sharedPreferences:SharedPreferences? = null

    fun getSharedPreferences(context: Context): SharedPreferences {
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences("account", MODE_PRIVATE)
        }
        return sharedPreferences!!
    }
}