package com.momon.catatanhutang.fragment

import android.app.AlertDialog
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
import com.momon.catatanhutang.databinding.FragmentEditHutangBinding
import com.momon.catatanhutang.model.Hutang
import com.momon.catatanhutang.viewmodel.HutangViewModel


class EditHutangFragment : Fragment() {

    private val args by navArgs<EditHutangFragmentArgs>()
    private lateinit var hutangViewModel : HutangViewModel

    private lateinit var binding : FragmentEditHutangBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEditHutangBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.apply {
            tvIduser.text = args.current.id_user.toString()
            etHutang.setText(args.current.hutang)
            etTgl.setText(args.current.tgl)
            etJmlh.setText(args.current.jmlh)
        }
        requireActivity().onBackPressedDispatcher.addCallback(this){
            findNavController().popBackStack()
        }
        hutangViewModel = ViewModelProvider(this)[HutangViewModel::class.java]

        val pilihan = resources.getStringArray(R.array.pilih)
        val adapter1 = context?.let { ArrayAdapter(it, R.layout.list_pilihan, pilihan) }
        binding.etHutang.setAdapter(adapter1)

        val datapicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date")
            .build()

        binding.tgl.setOnClickListener {
            datapicker.show(childFragmentManager, "data_range_piker")
            datapicker.addOnPositiveButtonClickListener{
                binding.etTgl.setText(datapicker.headerText)
            }
        }


        binding.btnHapus.setOnClickListener {
            deleteHutang()
        }
        binding.btnUpdate.setOnClickListener {
            updateHutang()
        }


        return view
    }

    private fun updateHutang(){
        val idUser = binding.tvIduser.text.toString()
        val hutang = binding.etHutang.text.toString()
        val tgl = binding.etTgl.text.toString()
        val jml = binding.etJmlh.text.toString()

        if (inputChek(idUser,hutang,tgl, jml)){
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Ubah Data User")
            builder.setMessage("Apakah Anda Ingin Mengubah Data hutang ?")
            builder.setPositiveButton(R.string.ya) { _, _ ->
                val iduser = args.current.id_user
                val hutang1 = Hutang(args.current.id, iduser, hutang, tgl, jml)
                hutangViewModel.updateHutang(hutang1)
                Toast.makeText(context, "Data Berhasil di ubah", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton(R.string.tidak) { _, _ ->
                Toast.makeText(context, "Batal", Toast.LENGTH_SHORT).show()
            }.show()
        }else{
            Toast.makeText(context, "Harap di isi !", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputChek(iduser: String,hutang: String, tgl : String, jml: String):Boolean{
        return !(TextUtils.isEmpty(iduser)  && TextUtils.isEmpty(hutang) && TextUtils.isEmpty(tgl) && TextUtils.isEmpty(jml))
    }

    private fun deleteHutang(){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Hati-Hati !")
        builder.setMessage("Apakah Anda Ingin Mengapus Data ?")
        builder.setPositiveButton(R.string.ya){ _, _ ->
            hutangViewModel.deleteHutang(args.current)
            Toast.makeText(context, "Data telah di hapus", Toast.LENGTH_SHORT).show()
            val action = EditHutangFragmentDirections.actionEditHutangFragmentToListUserFragment()
            findNavController().navigate(action)
        }
        builder.setNegativeButton(R.string.tidak) { _, _ ->
            Toast.makeText(context, "Batal", Toast.LENGTH_SHORT).show()
        }.show()
    }

}

