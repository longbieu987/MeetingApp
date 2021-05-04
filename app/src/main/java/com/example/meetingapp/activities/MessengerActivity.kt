package com.example.meetingapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.meetingapp.R
import com.example.meetingapp.databinding.ActivityMessengerBinding

class MessengerActivity : AppCompatActivity() {
    lateinit var binding : ActivityMessengerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessengerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

    }
    fun initView(){
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.searchViewMessenger.setOnSearchClickListener {

        }
    }
}