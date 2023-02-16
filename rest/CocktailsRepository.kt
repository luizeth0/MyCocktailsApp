package com.example.mycocktailsapp.rest

import android.content.ContentValues
import android.util.Log
import com.example.mycocktailsapp.model.CocktailResponse
import com.example.mycocktailsapp.utils.FailureResponse
import com.example.mycocktailsapp.utils.NullMusicResponse
import com.example.mycocktailsapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CocktailsRepository {
    fun getCocktails(name: String): Flow<UIState<CocktailResponse>>
}

class CocktailsRepositoryImpl @Inject constructor (
    private val cocktailsApi: CocktailsApi
): CocktailsRepository {
    override fun getCocktails(name: String): Flow<UIState<CocktailResponse>> = flow {
        emit(UIState.LOADING)

        try {
            val response = cocktailsApi.getCocktails(name)
            Log.d(ContentValues.TAG, "Hello1: $response")
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d(ContentValues.TAG, "Hello2: $it")
                    emit(UIState.SUCCESS(it))
                } ?: throw NullMusicResponse()
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }

    }


}