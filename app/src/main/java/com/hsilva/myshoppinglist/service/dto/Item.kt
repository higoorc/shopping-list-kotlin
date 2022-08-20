package com.hsilva.myshoppinglist.service.dto

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("isChecked")
    val isChecked: Boolean,
)