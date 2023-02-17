package com.example.mycocktailsapp.rest

import android.util.Log
import com.example.mycocktailsapp.model.CocktailResponse
import com.example.mycocktailsapp.utils.FailureResponse
import com.example.mycocktailsapp.utils.NullMusicResponse
import com.example.mycocktailsapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val TAG = "CocktailsRepository"

interface CocktailsRepository {
    fun getCocktails(): Flow<UIState<CocktailResponse>>
}

class CocktailsRepositoryImpl @Inject constructor (
    private val cocktailsApi: CocktailsApi
): CocktailsRepository {
    override fun getCocktails(): Flow<UIState<CocktailResponse>> = flow {
        emit(UIState.LOADING)

        try {
            val response = cocktailsApi.getCocktails()
            Log.d(TAG, "Hello1: $response")
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d(TAG, "Hello2: $it")
                    emit(UIState.SUCCESS(it))
                } ?: throw NullMusicResponse()
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            Log.e(TAG, "getCocktails: $e")
            emit(UIState.ERROR(e))
        }

    }

}