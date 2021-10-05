package com.momon.catatanhutang.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.momon.catatanhutang.R
import com.momon.catatanhutang.adapter.UserAdapter
import com.momon.catatanhutang.databinding.FragmentListUserBinding
import com.momon.catatanhutang.viewmodel.HutangViewModel
import com.momon.catatanhutang.viewmodel.UserViewModel


class ListUserFragment : Fragment() {
    private lateinit var binding : FragmentListUserBinding
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var hutangViewModel: HutangViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentListUserBinding.inflate(inflater, container, false)
        val view = binding.root
        val adapter = UserAdapter()
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)

        }


        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        hutangViewModel = ViewModelProvider(this)[HutangViewModel::class.java]

        mUserViewModel.readAllData.observe(viewLifecycleOwner, {
            adapter.setData(it)

        })

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.move_menu, menu)
        inflater.inflate(R.menu.delete_user,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add){
            tambahUser()
        }
        if (item.itemId == R.id.menu_delete){
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tambahUser(){
        val action = ListUserFragmentDirections.actionListUserFragmentToAddUserFragment()
        findNavController().navigate(action)
    }

    private fun deleteAllUser(){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Hati-Hati !")
        builder.setMessage("Apakah Anda ingin mengapus semua data ?")
        builder.setPositiveButton(R.string.ya) { _, _ ->
            mUserViewModel.deleteAllUser()
            hutangViewModel.deleteAllHutang1()
            Toast.makeText(context, "Data telah di hapus", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton(R.string.tidak) { _, _ ->
            Toast.makeText(context, "Batal", Toast.LENGTH_SHORT).show()
        }.show()

    }


}