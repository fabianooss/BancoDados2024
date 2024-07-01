package com.senac.bancodados2024.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.senac.bancodados2024.bd.AppDatabase
import com.senac.bancodados2024.dao.ProductDao
import com.senac.bancodados2024.entities.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModelFactory(val db: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return ProductViewModel(db.productDao) as T
    }
}

class ProductViewModel(val productDao: ProductDao): ViewModel() {

    private val _uiState = MutableStateFlow(Product())
    val uiState: StateFlow<Product> = _uiState.asStateFlow()

    fun updateName(name: String) {
        _uiState.update {
            it.copy(name = name)
        }
    }
    fun updateDescription(description: String) {
        _uiState.update {
            it.copy(description = description)
        }
    }
    fun updatePrice(price: Double) {
        _uiState.update {
            it.copy(price = price)
        }
    }

    private fun updateId(id: Long) {
        _uiState.update {
            it.copy(id = id)
        }
    }

    private fun new() {
        _uiState.update {
            it.copy(id = 0, name = "", description = "", price = 0.0)
        }
    }

    fun save() {
        viewModelScope.launch {
            val id = productDao.upsert(uiState.value)
            if (id > 0) {
                updateId(id)
            }
        }
    }

    fun saveNew() {
        save()
        new()
    }

    fun getAll() = productDao.getAll()




}