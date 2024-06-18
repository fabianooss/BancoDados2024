package com.senac.bancodados2024.bd

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.senac.bancodados2024.dao.ProductDao
import com.senac.bancodados2024.entities.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val productDao: ProductDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase = INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "product-db"
            ).build()
            INSTANCE = instance
            instance
        }
    }

}