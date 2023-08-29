package com.example.hangman

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WordViewModel : ViewModel() {
    private val repository = WordRepository(WordService.create())
    private val _wordResults = MutableLiveData<List<String>?>(null)
    val searchResults: LiveData<List<String>?> = _wordResults
    fun loadWordResults(query: Int) {
        viewModelScope.launch {
            val result = repository.loadRepositoriesSearch(query)
            _wordResults.value = result.getOrNull()
        }
    }
}