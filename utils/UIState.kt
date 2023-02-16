package com.example.mycocktailsapp.utils

sealed class UIState<out T> {
    object LOADING: UIState<Nothing>()
    data class SUCCESS<T> (val Response: T): UIState<T>()
    data class ERROR(val error: Exception): UIState<Nothing>()
}