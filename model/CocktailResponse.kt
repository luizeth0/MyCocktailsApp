package com.example.mycocktailsapp.model


import com.google.gson.annotations.SerializedName

data class CocktailResponse(
    @SerializedName("drinks")
    val drinks: List<Drink>? = null
)