package com.example.dbreferences.models

class Buyurtma {

    var id:Int = 1
    var name:String = ""
    var price: Int = 0
    var sotuvchi:Sotuvchi? = null
    var xaridor:Xaridor? = null

    constructor(id: Int, name: String, price: Int, sotuvchi: Sotuvchi?, xaridor: Xaridor?) {
        this.id = id
        this.name = name
        this.price = price
        this.sotuvchi = sotuvchi
        this.xaridor = xaridor
    }

    constructor(name: String, price: Int, sotuvchi: Sotuvchi?, xaridor: Xaridor?) {
        this.name = name
        this.price = price
        this.sotuvchi = sotuvchi
        this.xaridor = xaridor
    }


}