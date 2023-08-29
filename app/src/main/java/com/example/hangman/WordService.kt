package com.example.hangman

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WordService {
    @GET("word")
    suspend fun getWord(
        @Query("length") length: String
    ) : Response<List<String>>

    companion object {
        private const val BASE_URL = "https://random-word-api.herokuapp.com/"
        fun create() : WordService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(WordService::class.java)
        }
    }
}