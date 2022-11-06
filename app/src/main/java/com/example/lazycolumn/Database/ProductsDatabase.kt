package com.example.lazycolumn.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Products::class], version = 1, exportSchema = false)
abstract class ProductsDatabase:RoomDatabase() {
    abstract fun productsDao(): ProductsDao

    companion object {

        private var INSTANCE: ProductsDatabase? = null

        fun getDatabase(context: Context): ProductsDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProductsDatabase::class.java,
                        "ProductsDatabase"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }



}