package com.example.hangman

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WordResult(
    val words: List<String>
)
