package com.example.maxmaven_assignment_gaurav.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maxmaven_assignment_gaurav.data.model.BookModel
import com.example.maxmaven_assignment_gaurav.data.repository.FavouriteBookRepository
import com.example.maxmaven_assignment_gaurav.ui.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class FavouriteBookViewModel : ViewModel() {

    private val favouriteBookRepository: FavouriteBookRepository = FavouriteBookRepository()

    private val _uiState = MutableStateFlow<UiState<List<BookModel>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<BookModel>>> = _uiState

    fun getFavouriteBookDataFromLocalDb() {
        viewModelScope.launch {
            favouriteBookRepository.getBookDataFromLocalDb()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    fun deleteBook(bookModel: BookModel) {
        favouriteBookRepository.deleteBook(bookModel)
    }

}
