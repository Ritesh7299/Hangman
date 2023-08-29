package com.example.hangman

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WordRepository(
    private val service: WordService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun loadRepositoriesSearch(
        query: Int,
    ): Result<List<String>> =
        withContext(dispatcher) {
            try {
                val response = service.getWord(query.toString())
                if (response.isSuccessful) {
                    Log.d("WordRepository", response.toString())
                    Result.success(response.body() ?: listOf())
                } else {
                    Log.d("WordRepository", response.toString())
                    Result.failure(Exception(response.errorBody()?.string()))
                }
            } catch (e: Exception) {
                Log.d("WordRepository", "Failed")
                Log.d("WordRepository", e.toString())
                Result.failure(e)
            }
        }
}