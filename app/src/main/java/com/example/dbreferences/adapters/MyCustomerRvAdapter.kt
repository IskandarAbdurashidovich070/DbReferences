package com.example.dbreferences.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dbreferences.databinding.ItemRvBinding
import com.example.dbreferences.models.Xaridor

class MyCustomerRvAdapter(var list: List<Xaridor>) : RecyclerView.Adapter<MyCustomerRvAdapter.Vh>() {

    inner class Vh(var rvItemBinding: ItemRvBinding):RecyclerView.ViewHolder(rvItemBinding.root){
        fun onBind(xaridor: Xaridor, position: Int ){
           rvItemBinding.name.text = xaridor.name
           rvItemBinding.number.text = xaridor.number
           rvItemBinding.id.visibility = View.VISIBLE
           rvItemBinding.id.text = xaridor.address
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