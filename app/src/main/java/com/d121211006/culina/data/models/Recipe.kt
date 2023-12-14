package com.d121211006.culina.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Recipe(
    @SerialName("vegetarian")
    val vegetarian: Boolean? = null,
    @SerialName("vegan")
    val vegan: Boolean? = null,
    @SerialName("glutenFree")
    val glutenFree: Boolean? = null,
    @SerialName("dairyFree")
    val dairyFree: Boolean? = null,
    @SerialName("veryHealthy")
    val veryHealthy: Boolean? = null,
    @SerialName("cheap")
    val cheap: Boolean? = null,
    @SerialName("veryPopular")
    val veryPopular: Boolean? = null,
    @SerialName("sustainable")
    val sustainable: Boolean? = null,
    @SerialName("lowFodmap")
    val lowFodmap: Boolean? = null,
    @SerialName("weightWatcherSmartPoints")
    val weightWatcherSmartPoints: Int? = null,
    @SerialName("gaps")
    val gaps: String? = null,
    @SerialName("preparationMinutes")
    val preparationMinutes: Int? = null,
    @SerialName("cookingMinutes")
    val cookingMinutes: Int? = null,
    @SerialName("aggregateLikes")
    val aggregateLikes: Int? = null,
    @SerialName("healthScore")
    val healthScore: Int? = null,
    @SerialName("creditsText")
    val creditsText: String? = null,
    @SerialName("license")
    val license: String? = null,
    @SerialName("sourceName")
    val sourceName: String? = null,
    @SerialName("pricePerServing")
    val pricePerServing: Double? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("readyInMinutes")
    val readyInMinutes: Int? = null,
    @SerialName("servings")
    val servings: Int? = null,
    @SerialName("sourceUrl")
    val sourceUrl: String? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("imageType")
    val imageType: String? = null,
    @SerialName("summary")
    val summary: String? = null,
    @SerialName("cuisines")
    val cuisines: ArrayList<String> = arrayListOf(),
    @SerialName("dishTypes")
    val dishTypes: ArrayList<String> = arrayListOf(),
    @SerialName("diets")
    val diets: ArrayList<String> = arrayListOf(),
    @SerialName("occasions")
    val occasions: ArrayList<String> = arrayListOf(),
    @SerialName("instructions")
    val instructions: String? = null,
    @SerialName("originalId")
    val originalId: String? = null,
    @SerialName("spoonacularScore")
    val spoonacularScore: Double? = null,
    @SerialName("spoonacularSourceUrl")
    val spoonacularSourceUrl: String? = null
) : Parcelable

