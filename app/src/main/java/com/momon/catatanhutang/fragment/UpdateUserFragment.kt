package com.momon.catatanhutang.fragment

import android.app.AlertDialog
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
import androidx.navigation.fragment.navArgs
import com.momon.catatanhutang.R
import com.momon.catatanhutang.databinding.FragmentUpdateUserBinding
import com.momon.catatanhutang.model.User
import com.momon.catatanhutang.viewmodel.UserViewModel


class UpdateUserFragment : Fragment() {
    private lateinit var binding : FragmentUpdateUserBinding
    private val args by navArgs<UpdateUserFragmentArgs>()
    private lateinit var userViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateUserBinding.inflate(inflater, container, false)
        val view = binding.root


        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]


        binding.apply {
            etName.setText(args.current.usrname)
            etAlamat.setText(args.current.alamat)
            etNotel.setText(args.current.notel)
            btnUpdate.setOnClickListener{
                updateuser()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this){
            findNavController().popBackStack()
        }

        return view
    }

    private fun updateuser(){
        val username = binding.etName.text.toString()
        val alamat = binding.etAlamat.text.toString()
        val notel = binding.etNotel.text.toString()

        if (inputChek(username,alamat, notel)){
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Ubah Data User")
            builder.setMessage("Apakah Anda Ingin Mengubah Data User ?")
            builder.setPositiveButton(R.string.ya) { _, _ ->
                val user = User(args.current.id,username,alamat,notel)
                userViewModel.updateUser(user)
                Toast.makeText(context, "User berhasil di ubah", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateUserFragment_to_listUserFragment)
            }
            builder.setNegativeButton(R.string.tidak){ _, _ ->
                Toast.makeText(context, "Batal", Toast.LENGTH_SHORT).show()
            }.show()
        }else{
            Toast.makeText(context, "Harap isi form !", Toast.LENGTH_SHORT).show()
        }
    }
    private fun inputChek(username : String,alamat: String, notel : String):Boolean{
        return !(TextUtils.isEmpty(username) && TextUtils.isEmpty(alamat) && TextUtils.isEmpty(notel))
    }


}