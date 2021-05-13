package com.example.meetingapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meetingapp.adapter.ListFriendAdapter
import com.example.meetingapp.databinding.ActivityMessengerBinding
import com.example.meetingapp.model.User

class MessengerActivity : AppCompatActivity() {
    lateinit var binding : ActivityMessengerBinding
    lateinit var listFriendAdapter : ListFriendAdapter
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
        var listFriend = ArrayList<User>()
        listFriend.add(User("Long","nguyenhieulong1109@gmail.com","Longbieu987","1"))
        listFriend.add(User("Duy","duy1109@gmail.com","Duy987","2"))
        listFriendAdapter = ListFriendAdapter(listFriend)
        binding.listFriendMess.adapter = listFriendAdapter
        binding.listFriendMess.layoutManager = LinearLayoutManager(this)

    }
}