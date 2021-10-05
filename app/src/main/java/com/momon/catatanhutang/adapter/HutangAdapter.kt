package com.momon.catatanhutang.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.momon.catatanhutang.databinding.ItemHutangBinding
import com.momon.catatanhutang.fragment.ListHutangFragmentDirections
import com.momon.catatanhutang.model.Hutang


class HutangAdapter : RecyclerView.Adapter<HutangAdapter.HutangViewHolder>() {

    private var hutang = emptyList<Hutang>()



    @SuppressLint("NotifyDataSetChanged")
    fun setData(hutang1: List<Hutang>){
        hutang = hutang1
        notifyDataSetChanged()
    }



    inner class HutangViewHolder(private val binding : ItemHutangBinding): RecyclerView.ViewHolder(binding.root){
        val item : LinearLayout = binding.imgEdit
        fun bind(hutang: Hutang){
            binding.apply {
                tvHutang.text = hutang.hutang
                tvTgl.text = hutang.tgl
                tvJml.text = hutang.jmlh
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HutangViewHolder {
        val view = ItemHutangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  HutangViewHolder((view))
    }

    override fun onBindViewHolder(holder: HutangViewHolder, position: Int) {
        holder.bind(hutang[position])
        val current = hutang[position]
        holder.item.setOnClickListener{
            val action = ListHutangFragmentDirections.actionListHutangFragmentToEditHutangFragment(current)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return hutang.size
    }
}