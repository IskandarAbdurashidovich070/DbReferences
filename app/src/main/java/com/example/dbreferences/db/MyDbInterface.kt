package com.example.dbreferences.db

import com.example.dbreferences.models.Buyurtma
import com.example.dbreferences.models.Sotuvchi
import com.example.dbreferences.models.Xaridor

interface MyDbInterface {

    fun addSalesMan(sotuvchi: Sotuvchi)
    fun addCustomer(xaridor: Xaridor)
    fun addOrders(buyurtma: Buyurtma)


    fun getSalesman():List<Sotuvchi>
    fun getCustomer():List<Xaridor>
    fun getOrders():List<Buyurtma>


    fun getSalesmanById(id: Int): Sotuvchi
    fun getCustomerById(id: Int): Xaridor

}