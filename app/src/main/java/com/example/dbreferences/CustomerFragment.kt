package com.example.dbreferences

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.dbreferences.adapters.MyCustomerRvAdapter
import com.example.dbreferences.databinding.FragmentCustomerBinding
import com.example.dbreferences.databinding.ItemDialogBinding
import com.example.dbreferences.db.MyDbHelper
import com.example.dbreferences.models.Xaridor


class CustomerFragment : Fragment() {
    private lateinit var binding: FragmentCustomerBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var list: ArrayList<Xaridor>
    private lateinit var rvAdapter: MyCustomerRvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomerBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(context)
        list = ArrayList()
        list.addAll(myDbHelper.getCustomer())
        rvAdapter = MyCustomerRvAdapter(list)
        binding.rv.adapter = rvAdapter

        binding.btnAdd.setOnClickListener {
            var dialog = AlertDialog.Builder(binding.root.context).create()
            var itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
            itemDialogBinding.edtAddress.visibility = View.VISIBLE
            itemDialogBinding.tvXaridor.visibility = View.VISIBLE
            itemDialogBinding.edtNumber.visibility = View.VISIBLE
            dialog.setView(itemDialogBinding.root)
            dialog.show()

            itemDialogBinding.btnSave.setOnClickListener {
                
                var xaridor = Xaridor(itemDialogBinding.edtName.text.toString(), itemDialogBinding.edtNumber.text.toString(), itemDialogBinding.edtAddress.text.toString())
                myDbHelper.addCustomer(xaridor)
                list.add(xaridor)
                rvAdapter.notifyItemInserted(list.size-1)
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }

        }



        return binding.root
    }

}