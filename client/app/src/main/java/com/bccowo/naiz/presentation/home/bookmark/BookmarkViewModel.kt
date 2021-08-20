package com.bccowo.naiz.presentation.home.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.usecase.CandiUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookmarkViewModel(private val candiUseCase: CandiUseCase) : ViewModel() {
    fun getBookmark() = candiUseCase.getBookmarkedCandi()

    fun removeCandiFromBookmark(candi: Candi) {
        viewModelScope.launch(Dispatchers.IO) {
            candiUseCase.removeBookmark(candi)
        }
    }

    fun removeAllBookmark() {
        viewModelScope.launch(Dispatchers.IO) {
            candiUseCase.removeAllBookmarks()
        }
    }
}