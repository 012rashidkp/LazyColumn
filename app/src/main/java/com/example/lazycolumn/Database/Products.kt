package com.example.lazycolumn.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Products(
    @PrimaryKey(autoGenerate = true)
    val productid:Int,
    @ColumnInfo(name = "productName")
    val productName:String,
    @ColumnInfo(name = "productdesc")
    val productdesc:String,
    @ColumnInfo(name = "productprice")
    val productprice:Double,
    @ColumnInfo(name = "productqty")
    val productqty:Long
)
