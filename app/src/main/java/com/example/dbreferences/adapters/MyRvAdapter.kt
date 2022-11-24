package com.example.dbreferences.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dbreferences.databinding.ItemRvBinding
import com.example.dbreferences.models.Sotuvchi


class MyRvAdapter(var list: List<Sotuvchi>) : RecyclerView.Adapter<MyRvAdapter.Vh>() {

    inner class Vh(var rvItemBinding: ItemRvBinding):RecyclerView.ViewHolder(rvItemBinding.root){
        fun onBind(sotuvchi: Sotuvchi, position: Int ){
           rvItemBinding.name.text = sotuvchi.name
           rvItemBinding.number.text = sotuvchi.number
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context) , parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

}