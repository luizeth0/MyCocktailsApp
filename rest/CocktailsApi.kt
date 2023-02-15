package com.example.mycocktailsapp.rest

import com.example.mycocktailsapp.model.CocktailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailsApi {

    @GET(PATH)
    suspend fun getCocktails(
        @Query("?s") name: String,
    ): Response<CocktailResponse>


    companion object {

        // www.thecocktaildb.com/api/json/v1/1/search.php?s=
        // S = BY NAMe
        // F = FIRST LETTER
        // I = INGREDIENT NAME
        const val BASE_URL = "https://www.thecocktaildb.com/"
        private const val PATH = "api/json/v1/1/search.php?"
    }

}