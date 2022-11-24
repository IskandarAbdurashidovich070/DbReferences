package com.example.dbreferences

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.dbreferences.adapters.MyOrderAdapter
import com.example.dbreferences.databinding.FragmentXaridorBinding
import com.example.dbreferences.databinding.ItemDialogBinding
import com.example.dbreferences.db.MyDbHelper
import com.example.dbreferences.models.Buyurtma

class XaridorFragment : Fragment() {
    private lateinit var binding: FragmentXaridorBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var list: ArrayList<Buyurtma>
    private lateinit var adapter: MyOrderAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentXaridorBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(context)
        list = ArrayList()
        list.addAll(myDbHelper.getOrders())
        adapter = MyOrderAdapter(list)
        binding.rv.adapter = adapter

        binding.btnAdd.setOnClickListener {
            val dialog = AlertDialog.Builder(binding.root.context).create()
            val dialogBinding = ItemDialogBinding.inflate(layoutInflater)
            dialog.setView(dialogBinding.root)
            dialogBinding.edtPrice.visibility = View.VISIBLE
            dialogBinding.tvBuyrtma.visibility = View.VISIBLE
            dialogBinding.spinnerSotuv.visibility = View.VISIBLE
            dialogBinding.spinnerXarid.visibility = View.VISIBLE
            dialog.show()

            val listSotuvchi = myDbHelper.getSalesman()
            val listSutuvchiName = ArrayList<String>()
            listSotuvchi.forEach {
                listSutuvchiName.add(it.name)
            }

            val sAdapter = ArrayAdapter<String>(binding.root.context, android.R.layout.simple_list_item_1, listSutuvchiName)
            dialogBinding.spinnerSotuv.adapter = sAdapter


            val listXarior = myDbHelper.getCustomer()
            val listXaridorName = ArrayList<String>()
            listXarior.forEach {
                listXaridorName.add(it.name)
            }
            var xAdapter = ArrayAdapter<String>(binding.root.context, android.R.layout.simple_list_item_1, listXaridorName)
            dialogBinding.spinnerXarid.adapter = xAdapter

            dialogBinding.btnSave.setOnClickListener {
                val buyurtma = Buyurtma(
                    dialogBinding.edtName.text.toString(),
                    dialogBinding.edtPrice.text.toString().toInt(),
                    listSotuvchi[dialogBinding.spinnerSotuv.selectedItemPosition],
                    listXarior[dialogBinding.spinnerXarid.selectedItemPosition]
                )
                myDbHelper.addOrders(buyurtma)
                list.add(buyurtma)
                adapter.notifyItemInserted(list.size-1)
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }


        }



        return binding.root
       }
}