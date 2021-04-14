package com.example.meetingapp.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import com.example.meetingapp.R
import com.example.meetingapp.activities.LoginActivity
import com.example.meetingapp.activities.MainActivity

class MyService : Service() {

    var binder : IBinder = MyBinder()

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this,"Service create",Toast.LENGTH_SHORT).show()
    }
    @SuppressLint("NewApi")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this,"Service onStartCommand",Toast.LENGTH_SHORT).show()
//        val pendingIntent: PendingIntent =
//                Intent(this, MainActivity::class.java).let { notificationIntent ->
//                    PendingIntent.getActivity(this, 0, notificationIntent, 0)
//                }
//
//        val notification: Notification = Notification.Builder(this,"testChannel")
//                .setContentTitle("Long")
//                .setContentText("Test Service")
//                .setSmallIcon(R.drawable.ic_back)
//                .setContentIntent(pendingIntent)
//                .build()
//        startForeground(1,notification)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"Service onDestroy",Toast.LENGTH_SHORT).show()

    }
    @SuppressLint("NewApi")
    override fun onBind(intent: Intent?): IBinder? {
//        Toast.makeText(this,"Service onBind",Toast.LENGTH_SHORT).show()
//        val i = Intent(this,LoginActivity::class.java)
//        val pendingInten = PendingIntent.getActivity(this,0,i,0)
//        val notification = Notification.Builder(this,"channel")
//                .setContentTitle("Long")
//                .setContentText("Test Service")
//                .setSmallIcon(R.drawable.ic_back)
//                .setContentIntent(pendingInten)
//                .build()
//        startForeground(1,notification)
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Toast.makeText(this,"Service onUnbind",Toast.LENGTH_SHORT).show()
        return super.onUnbind(intent)
    }

    inner class MyBinder: Binder() {
        fun getService():MyService = this@MyService
    }
    fun sum(a : String, b:String) : String{
        return "Email : $a\nPassword : $b"
    }
}