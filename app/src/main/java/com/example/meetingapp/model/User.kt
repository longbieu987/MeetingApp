package com.example.meetingapp.model

data class User
(
        var name: String? = null,
        var email: String,
        var password:String,
        var friends:List<User>? = null
) {

}