package com.example.mycocktailsapp.utils

class NullMusicResponse(message: String = "Music response is null") : Exception(message)
class FailureResponse(message: String?) : Exception(message)