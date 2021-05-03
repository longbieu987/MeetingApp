@file:Suppress("DEPRECATION")

package com.example.meetingapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.meetingapp.R
import com.example.meetingapp.database.SharedPreferencesData
import com.example.meetingapp.databinding.ActivityLoginBinding
import com.example.meetingapp.model.User
import com.example.meetingapp.viewmodel.AccountViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding: ActivityLoginBinding
    lateinit var model : AccountViewModel
    lateinit var sharedPreferences: SharedPreferencesData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        initialization()


    }

    @SuppressLint("NewApi")
    private fun initialization() {
        loginBinding.tvSignUp.setOnClickListener {
            loginBinding.tvSignUp.setShadowLayer(4f, 2f, 2f, resources.getColor(R.color.shadow_color))
            startActivity(Intent(this, SignUpActivity::class.java))

        }
        loginBinding.btnLogin.setOnClickListener {
            if (loginBinding.edtEmail.text.isNullOrEmpty() || loginBinding.edtPassword.text.isNullOrEmpty()) {
                Toast.makeText(this, "Xin hãy nhập đầy dủ thông tin", Toast.LENGTH_SHORT).show()
            } else {
                loginBinding.btnLogin.startAnimation()
                checkAccount()
            }


        }
        model = ViewModelProvider(this).get(AccountViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        loginBinding.btnLogin.dispose()
    }

    private fun checkAccount() {
        val firebaseAuth = Firebase.auth
        firebaseAuth.signInWithEmailAndPassword(loginBinding.edtEmail.text.toString(), loginBinding.edtPassword.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        model.getUser(firebaseAuth)
                        model.getSharedPreferences(this)

                        startActivity(Intent(this, MainActivity::class.java))
                        loginBinding.btnLogin.revertAnimation {
                            loginBinding.btnLogin.text = resources.getString(R.string.login)
                            loginBinding.btnLogin.setTextColor(resources.getColor(R.color.white))
                            loginBinding.btnLogin.setBackgroundResource(R.drawable.background_btn_login)
                        }


                    } else {
                        loginBinding.btnLogin.revertAnimation {
                            loginBinding.btnLogin.text = resources.getString(R.string.login)
                            loginBinding.btnLogin.setTextColor(resources.getColor(R.color.white))
                            loginBinding.btnLogin.setBackgroundResource(R.drawable.background_btn_login)
                        }
                        Toast.makeText(this, " ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
    }
}