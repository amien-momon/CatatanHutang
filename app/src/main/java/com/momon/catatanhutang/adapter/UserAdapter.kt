package com.momon.catatanhutang.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.momon.catatanhutang.databinding.ItemUserBinding
import com.momon.catatanhutang.fragment.ListUserFragmentDirections
import com.momon.catatanhutang.model.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var users = emptyList<User>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(user: List<User>){
        users = user
        notifyDataSetChanged()
    }


    inner class UserViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root){
        val itemuser : LinearLayout = binding.userListLayout
        fun bind(user: User){
            binding.apply {
                tvName.text = user.usrname
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  UserViewHolder((view))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
        val current = users[position]
        holder.itemuser.setOnClickListener {
            val action = ListUserFragmentDirections.actionListUserFragmentToListHutangFragment(current)
            holder.itemView.findNavController().navigate(action)
        }


    }

    override fun getItemCount(): Int {
        return users.size
    }
}