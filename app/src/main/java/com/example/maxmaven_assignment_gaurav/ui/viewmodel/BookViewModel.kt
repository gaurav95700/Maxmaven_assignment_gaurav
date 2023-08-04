package com.example.maxmaven_assignment_gaurav.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maxmaven_assignment_gaurav.data.model.BookModel
import com.example.maxmaven_assignment_gaurav.data.repository.BookRepository
import com.example.maxmaven_assignment_gaurav.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {

    private val bookRepository: BookRepository = BookRepository()

    private val _uiState = MutableStateFlow<UiState<List<BookModel>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<BookModel>>> = _uiState

    fun getBookData() {
        viewModelScope.launch {
            bookRepository.getBookData()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    fun insertBookDataToLocalDb(bookModel: BookModel) {
        bookRepository.insertBookDataToLocalDb(bookModel)
    }

}