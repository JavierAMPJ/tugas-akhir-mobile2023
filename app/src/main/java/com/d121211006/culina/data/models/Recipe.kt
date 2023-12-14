package com.d121211006.culina.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Recipe(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String,
    @SerialName("image")
    val image: String,
    @SerialName("imageType")
    val imageType: String,
    val ingredients: List<Ingredient>,
    val steps: List<String>
) : Parcelable

@Parcelize
@Serializable
data class Ingredient(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("amount")
    val amount: Double,
    @SerialName("unit")
    val unit: String
) : Parcelable
