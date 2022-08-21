package com.hsilva.myshoppinglist.service

import com.hsilva.myshoppinglist.service.dto.Item
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ShoppingListApi {

    @PUT("items/{id}.json")
    suspend fun addItem( // title configured as identifier for tasks
        @Path("id") id: String?,
        @Body item: Item
    ): Response<Item>

    @GET("items/{id}.json")
    suspend fun getItem(
        @Path("id") id: String
    ): Response<Item> // could be used for fetching details or checking if item already exists

    @GET("items/.json")
    suspend fun getAllItems(): Response<Map<String, Item>>
    // note that we'll receive a Map here from firebase with key being the identifier

    @DELETE("items/{id}.json")
    suspend fun deleteItem(
        @Path("id") id: String
    ): Response<Item>

    @PATCH("items/{id}.json")
    suspend fun updateItem(
        @Path("id") id: String,
        @Body item: Item
    ): Response<Item>

}