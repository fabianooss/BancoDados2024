package com.senac.bancodados2024.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0
) {
}