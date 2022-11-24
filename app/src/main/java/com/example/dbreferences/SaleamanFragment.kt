package com.example.dbreferences

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.dbreferences.adapters.MyRvAdapter
import com.example.dbreferences.databinding.FragmentHomeBinding
import com.example.dbreferences.databinding.FragmentSaleamanBinding
import com.example.dbreferences.databinding.ItemDialogBinding
import com.example.dbreferences.db.MyDbHelper
import com.example.dbreferences.models.Sotuvchi

class SaleamanFragment : Fragment() {
    private lateinit var binding: FragmentSaleamanBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var rvAdapter: MyRvAdapter
    private lateinit var list: ArrayList<Sotuvchi>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaleamanBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(context)
        list = ArrayList()
        list.addAll(myDbHelper.getSalesman())
        rvAdapter = MyRvAdapter(list)
        binding.rv.adapter = rvAdapter

        binding.btnAdd.setOnClickListener {
            val dialog = AlertDialog.Builder(binding.root.context).create()
            val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
            itemDialogBinding.tvSotuv.visibility = View.VISIBLE
            itemDialogBinding.edtNumber.visibility =View.VISIBLE
            dialog.setView(itemDialogBinding.root)
            dialog.show()


            itemDialogBinding.btnSave.setOnClickListener {
                val sotuvchi = Sotuvchi(itemDialogBinding.edtName.text.toString(), itemDialogBinding.edtNumber.text.toString())
                myDbHelper.addSalesMan(sotuvchi)
                list.add(sotuvchi)
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                dialog.cancel()
                rvAdapter.notifyItemInserted(list.size-1)
            }

        }



        return binding.root
      }
}