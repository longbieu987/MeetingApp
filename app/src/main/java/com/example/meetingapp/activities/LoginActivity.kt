package com.example.meetingapp.activities

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.meetingapp.R
import com.example.meetingapp.databinding.ActivityLoginBinding
import com.example.meetingapp.service.MyService
import kotlinx.coroutines.delay
import kotlin.concurrent.thread
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding: ActivityLoginBinding
    lateinit var myService: MyService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        initialization()

    }

     @SuppressLint("NewApi")
     private fun initialization() {
//         loginBinding.tvSignUp.setOnClickListener { startActivity(Intent(this, SignUpActivity::class.java)) }
//         loginBinding.btnLogin.setOnClickListener { Toast.makeText(this,"Login",Toast.LENGTH_SHORT).show() }
         val intent = Intent(this,MyService::class.java)
         val connection : ServiceConnection = object :ServiceConnection{
             override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                 Toast.makeText(applicationContext,"Service onServiceConnected",Toast.LENGTH_SHORT).show()
                 val binder : MyService.MyBinder = service as MyService.MyBinder
                 myService = binder.getService()
             }

             override fun onServiceDisconnected(name: ComponentName?) {
                 Toast.makeText(applicationContext,"Service onServiceDisconnected",Toast.LENGTH_SHORT).show()

             }
         }

         loginBinding.tvSignUp.setOnClickListener{
            bindService(intent,connection, BIND_AUTO_CREATE)
         }
         loginBinding.btnLogin.setOnClickListener{
            var result =myService.sum(loginBinding.edtEmail.text.toString(), loginBinding.edtPassword.text.toString())
             Toast.makeText(this,result,Toast.LENGTH_SHORT).show()
         }
         loginBinding.imageHeader.setOnClickListener {
            unbindService(connection)
         }
     }
}