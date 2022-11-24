package com.example.dbreferences.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.dbreferences.models.Buyurtma
import com.example.dbreferences.models.Sotuvchi
import com.example.dbreferences.models.Xaridor

class MyDbHelper(context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION), MyDbInterface {

    companion object {
        var DB_NAME = "market_db"
        var DB_VERSION = 1

        var SOTUVCHI_TABLE = "sotuvchi_table"
        var SOTUVCHI_ID = "id"
        var SOTUVCHI_NAME = "name"
        var SOTUVCHI_NUMBER = "number"


        var XARIDOR_TABLE = "xaridor_table"
        var XARIDOR_ID = "id"
        var XARIDOR_NAME = "name"
        var XARIDOR_NUMBER = "number"
        var XARIDOR_ADDRESS = "address"

        var BUYURTMA_TABLE = "buyurtma_table"
        var BUYURTMA_ID = "id"
        var BUYURTMA_NAME = "name"
        var BUYURTMA_PRICE = "price"
        var BUYURTMA_SOTUVCHI_ID = "sotuvchi_id"
        var BUYURTMA_XARIDOR_ID = "xaridor_id"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val querySotuvchi =
            "create table $SOTUVCHI_TABLE ($SOTUVCHI_ID integer not null primary key autoincrement unique, $SOTUVCHI_NAME text not null , $SOTUVCHI_NUMBER text not null)"
        val queryXaridor =
            "create table $XARIDOR_TABLE ($XARIDOR_ID integer not null primary key autoincrement unique, $XARIDOR_NAME text not null , $XARIDOR_NUMBER text not null,  $XARIDOR_ADDRESS text not null )"
        val queryBuyurtma =
            "create table $BUYURTMA_TABLE ($BUYURTMA_ID integer not null primary key autoincrement unique, $BUYURTMA_NAME text not null, $BUYURTMA_PRICE integer not null, $BUYURTMA_SOTUVCHI_ID integer not null, $BUYURTMA_XARIDOR_ID integer not null, foreign key ($BUYURTMA_SOTUVCHI_ID) references $SOTUVCHI_TABLE ($SOTUVCHI_ID), foreign key ($BUYURTMA_XARIDOR_ID) references $XARIDOR_TABLE ($XARIDOR_TABLE))"
        p0?.execSQL(querySotuvchi)
        p0?.execSQL(queryXaridor)
        p0?.execSQL(queryBuyurtma)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun addSalesMan(sotuvchi: Sotuvchi) {
        val database = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(SOTUVCHI_NAME, sotuvchi.name)
        contentValues.put(SOTUVCHI_NUMBER, sotuvchi.number)
        database.insert(SOTUVCHI_TABLE, null, contentValues)
        database.close()
    }

    override fun addCustomer(xaridor: Xaridor) {
        val database = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(XARIDOR_NAME, xaridor.name)
        contentValues.put(XARIDOR_NUMBER, xaridor.number)
        contentValues.put(XARIDOR_ADDRESS, xaridor.address)
        database.insert(XARIDOR_TABLE, null, contentValues)
        database.close()
    }

    override fun addOrders(buyurtma: Buyurtma) {
        val database = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(BUYURTMA_NAME, buyurtma.name)
        contentValues.put(BUYURTMA_PRICE, buyurtma.price)
        contentValues.put(BUYURTMA_SOTUVCHI_ID, buyurtma.sotuvchi?.id)
        contentValues.put(BUYURTMA_XARIDOR_ID, buyurtma.xaridor?.id)
        database.insert(BUYURTMA_TABLE, null, contentValues)
        database.close()
    }

    override fun getSalesman(): List<Sotuvchi> {
        val list = ArrayList<Sotuvchi>()
        val query = "select * from $SOTUVCHI_TABLE"
        val database = readableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    Sotuvchi(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                    )
                )
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun getCustomer(): List<Xaridor> {
        val list = ArrayList<Xaridor>()
        val query = "select * from $XARIDOR_TABLE"
        val database = readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {

            do {
                list.add(
                    Xaridor(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                    )
                )
            } while (cursor.moveToNext())

        }
        return list
    }

    override fun getOrders(): List<Buyurtma> {
        val list = ArrayList<Buyurtma>()
        val database = readableDatabase
        val query = "select * from $BUYURTMA_TABLE"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    Buyurtma(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        getSalesmanById(cursor.getInt(3)),
                        getCustomerById(cursor.getInt(4))

                    )
                )
            } while (cursor.moveToNext())
        }

        return list
    }

    override fun getSalesmanById(id: Int): Sotuvchi {
        val database = readableDatabase
        val cursor = database.query(
            SOTUVCHI_TABLE,
            arrayOf(
                SOTUVCHI_ID,
                SOTUVCHI_NAME,
                SOTUVCHI_NUMBER
            ),
            "$SOTUVCHI_ID = ?",
            arrayOf(id.toString()),
            null, null, null
        )
        cursor.moveToFirst()
        val sotuvchi = Sotuvchi(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2)
        )
        return sotuvchi
    }

    override fun getCustomerById(id: Int): Xaridor {
        val database = readableDatabase
        val cursor = database.query(
            XARIDOR_TABLE,
            arrayOf(
                XARIDOR_ID,
                XARIDOR_NAME,
                XARIDOR_NUMBER,
                XARIDOR_ADDRESS
            ),
            "$XARIDOR_ID = ?",
            arrayOf(id.toString()),
            null, null, null
        )
        val xaridor = Xaridor(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3)
        )
        return xaridor
    }
}