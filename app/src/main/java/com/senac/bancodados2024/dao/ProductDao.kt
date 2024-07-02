package com.senac.bancodados2024.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.senac.bancodados2024.entities.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert
    fun insert(product: Product): Long

    @Update
    fun update(product: Product)

    @Upsert
    suspend fun upsert(product: Product): Long

    @Query("select * from product p order by p.name")
    fun getAll(): Flow<List<Product>>

    @Query("select * from product p where p.id = :id")
    suspend fun findById(id: Long) : Product?

    @Delete
    fun delete(product: Product)



}