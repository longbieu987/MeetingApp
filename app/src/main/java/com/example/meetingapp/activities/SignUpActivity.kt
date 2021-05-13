package com.example.meetingapp.activities


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.meetingapp.databinding.ActivitySignUpBinding
import com.example.meetingapp.model.Account
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.*


class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

    }
    private fun initView(){
        binding.backBtn.setOnClickListener { onBackPressed() }
        binding.btnSignUp.setOnClickListener {
            val database = FirebaseFirestore.getInstance()
            if(checkAccountValid() == "Success"){
                val user= Account(
                    binding.inputName.text.toString(),
                    binding.inputEmail.text.toString(),
                    binding.inputPassword.text.toString())

                val firebaseAuth = Firebase.auth
                firebaseAuth.createUserWithEmailAndPassword(
                    binding.inputEmail.text.toString(),
                    binding.inputPassword.text.toString())
                    .addOnSuccessListener {
                        val id = it.user!!.uid
                        Log.d("BBB",id)
                        database.collection("users")
                            .document(id)
                            .set(user)
                            .addOnSuccessListener {
                                Toast.makeText(this, "SignUp Success", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this, LoginActivity::class.java))
                            }
                            .addOnFailureListener {
                                Toast.makeText(this, "Create information fail", Toast.LENGTH_SHORT).show()
                            }
                    }
                    .addOnFailureListener {

                        Toast.makeText(this,"${it.message}",Toast.LENGTH_SHORT).show()
                    }
            }
            else{
                Toast.makeText(this,checkAccountValid(),Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun checkAccountValid() : String{
        when{
            binding.inputName.text.isNullOrEmpty() -> {
                return "Name không được để trống"
            }
            binding.inputEmail.text.isNullOrEmpty() -> {
                return "Email không được để trống"
            }
            !Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.text.toString()).matches() -> {
                return "Email nhập không hợp lệ"
            }
            binding.inputPassword.text.isNullOrEmpty() -> {
                return "Password không được để trống"
            }
            binding.inputConfirmPassword.text.isNullOrEmpty() -> {
                return "ConfirmPassword không được để trống"
            }
            binding.inputConfirmPassword.text.toString() != binding.inputPassword.text.toString() -> {
                return "ConfirmPassword phải giống với Password"
            }
            binding.inputPassword.text.toString().length <8 -> {
                return "Password phải có ít nhất 8 kí tự"
            }
            binding.inputPassword.text.toString().uppercase(Locale.ROOT) == binding.inputPassword.text.toString() -> {
                return "Password phải có ít nhất 1 kí tự viết hoa"
            }
            else -> {
                return "Success"
            }
        }




    }
}
