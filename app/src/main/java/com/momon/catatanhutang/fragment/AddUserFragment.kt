package com.momon.catatanhutang.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.momon.catatanhutang.databinding.FragmentAddUserBinding
import com.momon.catatanhutang.model.User
import com.momon.catatanhutang.viewmodel.UserViewModel


class AddUserFragment : Fragment() {
    private lateinit var  binding : FragmentAddUserBinding

    private lateinit var userViewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddUserBinding.inflate(inflater, container, false)
        val view = binding.root

        requireActivity().onBackPressedDispatcher.addCallback(this){
            findNavController().popBackStack()
        }

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.btnTambah.setOnClickListener {
            insertdatabase()
        }

        return view
    }

  fun insertdatabase(){
        val username = binding.etName.text.toString()
        val alamat = binding.etAlamat.text.toString()
        val notel = binding.etNotel.text.toString()

        if (inputChek(username,alamat, notel)){
            val user = User(0,username,alamat,notel)
            userViewModel.addUser(user)
            Toast.makeText(context, "User berhasil di tambahkan", Toast.LENGTH_SHORT).show()
            val action = AddUserFragmentDirections.actionAddUserFragmentToListUserFragment()
            findNavController().navigate(action)
        }else{
            Toast.makeText(context, "Coba input data ulang !", Toast.LENGTH_SHORT).show()
        }

    }
    private fun inputChek(username : String,alamat: String, notel : String):Boolean{
        return !(TextUtils.isEmpty(username) && TextUtils.isEmpty(alamat) && TextUtils.isEmpty(notel))
    }


}