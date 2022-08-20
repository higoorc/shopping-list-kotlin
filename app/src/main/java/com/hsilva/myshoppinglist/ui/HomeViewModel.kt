package com.hsilva.myshoppinglist.ui

import androidx.lifecycle.ViewModel
import com.hsilva.myshoppinglist.service.RetrofitInstance
import com.hsilva.myshoppinglist.service.dto.Item
import com.hsilva.myshoppinglist.utils.SingleLiveEvent
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

@DelicateCoroutinesApi
class HomeViewModel : ViewModel() {

    private lateinit var items: SingleLiveEvent<List<Item>>

    fun getItems(): SingleLiveEvent<List<Item>> {
        if (!this::items.isInitialized) {
            items = SingleLiveEvent()
        }
        return items
    }

    init {
        addItem("Tarefa 1")
    }

    fun addItem(title: String) {
        val item = Item("1234", title, false)

        GlobalScope.launch {
            val response = try {
                RetrofitInstance.api.addItem(item.id, item)
            } catch (e: IOException) {
                return@launch
            } catch (e: HttpException) {
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                return@launch
            } else {
                return@launch
            }
        }
    }

    fun removeItem(item: Item) {
        GlobalScope.launch {
            val response = try {
                RetrofitInstance.api.deleteItem(item.id)
            } catch (e: IOException) {
                return@launch
            } catch (e: HttpException) {
                return@launch
            }

            if (true) {
                // update UI
                return@launch
            } else {
                return@launch
            }
        }
    }

    fun toggleItem(item: Item) {
        GlobalScope.launch {
            val response = try {
                RetrofitInstance.api.updateItem(item.id, item)
            } catch (e: IOException) {
                return@launch
            } catch (e: HttpException) {
                return@launch
            }

            if (true) {
                // update UI
                return@launch
            } else {
                return@launch
            }
        }
    }

    fun getAllItems(): Job =
        GlobalScope.launch {
            val response = try {
                RetrofitInstance.api.getAllItems()
            } catch (e: IOException) {
                return@launch
            } catch (e: HttpException) {
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                val allItems = response.body() as List<Item>
                items.postValue(allItems)
                return@launch
            } else {
                return@launch
            }
        }
}