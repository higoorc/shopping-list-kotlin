package com.hsilva.myshoppinglist.ui.home

import androidx.lifecycle.ViewModel
import com.hsilva.myshoppinglist.service.RetrofitInstance
import com.hsilva.myshoppinglist.service.dto.Item
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.util.*


@OptIn(DelicateCoroutinesApi::class)
class HomeViewModel : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing

    private val _state = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState>
        get() = _state

    init {
        getAllItems()
    }

    fun addItem(title: String) {
        if (title.isNotEmpty()) {
            val item = Item(UUID.randomUUID().toString(), title, false)

            GlobalScope.launch {
                val response = try {
                    RetrofitInstance.api.addItem(item.id, item)
                } catch (e: IOException) {
                    return@launch
                } catch (e: HttpException) {
                    return@launch
                }

                if (response.isSuccessful && response.body() != null) {
                    getAllItems()
                    return@launch
                } else {
                    return@launch
                }
            }
        }
    }

    fun updateItem(item: Item, isChecked: Boolean) {
        val updated = Item(item.id, item.name, isChecked)

        GlobalScope.launch {
            val response = try {
                RetrofitInstance.api.updateItem(item.id, updated)
            } catch (e: IOException) {
                return@launch
            } catch (e: HttpException) {
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                getAllItems()
                return@launch
            } else {
                return@launch
            }
        }
    }

    fun deleteItem(item: Item) =
        GlobalScope.launch {
            val response = try {
                RetrofitInstance.api.deleteItem(item.id)
            } catch (e: IOException) {
                return@launch
            } catch (e: HttpException) {
                return@launch
            }

            if (response.isSuccessful) {
                getAllItems()
                return@launch
            } else {
                return@launch
            }
        }

    fun getAllItems() {
        _isRefreshing.value = true

        GlobalScope.launch {
            val response = try {
                RetrofitInstance.api.getAllItems()
            } catch (e: IOException) {
                return@launch
            } catch (e: HttpException) {
                return@launch
            }

            if (response.isSuccessful) {
                if (response.body() != null) {
                    val mapItems = response.body() as Map<String, Item>
                    val listItems = mapItems.values.toList()

                    _state.value = HomeViewState(listItems)
                } else {
                    _state.value = HomeViewState(emptyList())
                }

                _isRefreshing.value = false
                return@launch
            } else {
                return@launch
            }
        }
    }
}

data class HomeViewState(
    val itemList: List<Item> = emptyList()
)

