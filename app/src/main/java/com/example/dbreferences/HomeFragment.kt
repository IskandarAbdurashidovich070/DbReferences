package com.example.dbreferences

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.dbreferences.databinding.ActivityMainBinding
import com.example.dbreferences.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.btnSotuvchi.setOnClickListener {
            findNavController().navigate(R.id.saleamanFragment)
        }

        binding.btnXaridor.setOnClickListener {
            findNavController().navigate(R.id.customerFragment)
        }
        binding.btnBuyurtma.setOnClickListener {
            findNavController().navigate(R.id.xaridorFragment)
        }




        return binding.root
    }

}