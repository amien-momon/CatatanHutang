package com.momon.catatanhutang.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import com.momon.catatanhutang.R
import com.momon.catatanhutang.databinding.FragmentAddHutangBinding
import com.momon.catatanhutang.model.Hutang
import com.momon.catatanhutang.viewmodel.HutangViewModel


class AddHutangFragment : Fragment() {
    private val args by navArgs<AddHutangFragmentArgs>()
    private lateinit var binding: FragmentAddHutangBinding

    private lateinit var hutangViewModel : HutangViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddHutangBinding.inflate(inflater, container, false)
        val view = binding.root

        requireActivity().onBackPressedDispatcher.addCallback(this){
            findNavController().popBackStack()
        }
        val pil ="Pilihan"
        binding.etHutang.setText(pil)
        val pilihan = resources.getStringArray(R.array.pilih)
        val adapter1 = context?.let { ArrayAdapter(it, R.layout.list_pilihan, pilihan) }
        binding.etHutang.setAdapter(adapter1)


        hutangViewModel = ViewModelProvider(this)[HutangViewModel::class.java]
        binding.btnTambah.setOnClickListener {
            tambahHutang()
        }

        val datapicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date")
            .build()

        binding.tgl.setOnClickListener {
            datapicker.show(childFragmentManager, "data_range_piker")
            datapicker.addOnPositiveButtonClickListener{
                binding.etTgl.setText(datapicker.headerText)
            }
        }


        return view
    }

    private fun tambahHutang(){
        val idUser = binding.tvIduser.text.toString()
        val hutang = binding.etHutang.text.toString()
        val tgl = binding.etTgl.text.toString()
        val jml = binding.etJmlh.text.toString()

        if (inputChek(idUser,hutang,tgl, jml)){
            val iduser = args.current.id
            val hutang1 = Hutang(0,iduser,hutang,tgl, jml)
            hutangViewModel.addHutang(hutang1)
            Toast.makeText(context, "Hutang berhasil di tambahkan", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addHutangFragment_to_listUserFragment)
        }else{
            Toast.makeText(context, "Coba input data ulang !", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputChek(iduser: String,hutang: String, tgl : String, jml: String):Boolean{
        return !(TextUtils.isEmpty(iduser)  && TextUtils.isEmpty(hutang) && TextUtils.isEmpty(tgl) && TextUtils.isEmpty(jml))
    }


}