package com.momon.catatanhutang.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.momon.catatanhutang.R
import com.momon.catatanhutang.adapter.HutangAdapter
import com.momon.catatanhutang.databinding.FragmentListHutangBinding
import com.momon.catatanhutang.viewmodel.HutangViewModel
import com.momon.catatanhutang.viewmodel.UserViewModel


class ListHutangFragment : Fragment() {
    private val args by navArgs<ListHutangFragmentArgs>()
    private lateinit var binding: FragmentListHutangBinding
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var hutangViewModel : HutangViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListHutangBinding.inflate(inflater, container, false)
        val view = binding.root


        requireActivity().onBackPressedDispatcher.addCallback(this){
            findNavController().popBackStack()
        }

        binding.apply {
            tvName.text = args.current.usrname
            tvAlamat.text = args.current.alamat
            tvNotel.text = args.current.notel

            btnTambahUtang.setOnClickListener {
                val current = args.current
                val action = ListHutangFragmentDirections.actionListHutangFragmentToAddHutangFragment(current)
                findNavController().navigate(action)
            }

        }
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        val adapter = HutangAdapter()
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
        hutangViewModel = ViewModelProvider(this)[HutangViewModel::class.java]

        hutangViewModel.readAllData(args.current.id).observe(viewLifecycleOwner,{
            adapter.setData(it)
        })

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_user,menu)
        inflater.inflate(R.menu.delete_user,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_update){
            moveupdate()
        }
        if (item.itemId == R.id.menu_delete){
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun moveupdate(){
        val current = args.current
        val action = ListHutangFragmentDirections.actionListHutangFragmentToUpdateUserFragment(current)
        findNavController().navigate(action)
    }

    private fun deleteAllUser(){
        val builder = AlertDialog.Builder(context)
        builder.apply {
            setTitle("Hati-Hati !")
            setMessage("Apakah Anda ingin mengapus semua data user ?")
            setPositiveButton(R.string.ya){ _, _ ->
                mUserViewModel.deleteUser(args.current)
                hutangViewModel.deleteAllHutang(args.current.id)
                Toast.makeText(context, "Data telah di hapus", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_listHutangFragment_to_listUserFragment)
            }
            setNegativeButton(R.string.tidak
            ) { _, _ ->
                Toast.makeText(context, "Batal", Toast.LENGTH_SHORT).show()
            }
        }.show()
    }
}