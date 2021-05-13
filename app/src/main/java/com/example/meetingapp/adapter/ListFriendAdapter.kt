package com.example.meetingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meetingapp.R
import com.example.meetingapp.model.User
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ListFriendAdapter(var list: List<User>) : RecyclerView.Adapter<ListFriendAdapter.Holder>() {

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvName  = itemView.findViewById<TextView>(R.id.tvName)
        var tvTimeOff  = itemView.findViewById<TextView>(R.id.timeOff)
        var tvContentPreview  = itemView.findViewById<TextView>(R.id.tvContentPreview)
        var imgAvata = itemView.findViewById<CircleImageView>(R.id.imgAvatar)

        fun bind(user: User){
            tvName.text = user.name
            tvTimeOff.text = user.email
            tvContentPreview.text = user.password
            Picasso.get().load(R.drawable.avatar).into(imgAvata)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_friend_messenger,parent,false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}