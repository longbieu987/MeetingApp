package com.example.meetingapp.database

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseFirestore {
    private var database : FirebaseFirestore? = null

    fun getDatabase() : FirebaseFirestore{
        if(database == null){
            database = FirebaseFirestore.getInstance()
        }
        return database!!
    }
}