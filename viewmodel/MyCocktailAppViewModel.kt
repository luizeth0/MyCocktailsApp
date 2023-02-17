package com.example.mycocktailsapp.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycocktailsapp.model.CocktailResponse
import com.example.mycocktailsapp.rest.CocktailsRepository
import com.example.mycocktailsapp.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyCocktailAppViewModel @Inject constructor(
    private val cocktailsRepository: CocktailsRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {



    private val names = ""



    private val _cocktail : MutableLiveData<UIState<CocktailResponse>> = MutableLiveData(UIState.LOADING)
    val cocktail : MutableLiveData<UIState<CocktailResponse>> get() = _cocktail

    /*private val _cocktailId: MutableLiveData<UIState<CocktailResponse>> = MutableLiveData(UIState.LOADING)
    val cocktailId: MutableLiveData<UIState<CocktailResponse>> get() = _cocktailId

    private val _cocktailName: MutableLiveData<UIState<CocktailResponse>> = MutableLiveData(UIState.LOADING)
    val cocktailName: MutableLiveData<UIState<CocktailResponse>> get() = _cocktailName
    */



    init {
        getCocktails()
    }

    private fun getCocktails() {

        //run {
            viewModelScope.launch(ioDispatcher) {
                cocktailsRepository.getCocktails().collect() {

                    _cocktail.postValue(it)
                    Log.d(ContentValues.TAG, "TEST: $_cocktail")

                }
            }
       // }

    }






}