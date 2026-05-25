package com.example.pam.modul_9.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam.modul_9.data.Book
import com.example.pam.modul_9.data.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {
    private val _bookUiState = MutableStateFlow<UIState<List<Book>>>(UIState.Loading)
    val bookUiState: StateFlow<UIState<List<Book>>> = _bookUiState

    fun listBooks() {
        viewModelScope.launch {
            _bookUiState.value = UIState.Loading
            repository.listBooks()
                .catch { e -> _bookUiState.value = UIState.Error(e.toString()) }
                .collect { bookList -> _bookUiState.value = UIState.Success(bookList) }
        }
    }

    fun insertBook(book: Book) {
        viewModelScope.launch {
            _bookUiState.value = UIState.Loading
            repository.insertBook(book).onCompletion {
                repository.listBooks().collect { bookList ->
                    _bookUiState.value = UIState.Success(bookList)
                }
            }.catch { e ->
                _bookUiState.value = UIState.Error(e.toString())
            }.collect()
        }
    }
}