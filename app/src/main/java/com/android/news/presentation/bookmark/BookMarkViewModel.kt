package com.android.news.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.news.domain.news_use_cases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(BookMarkState())
    val state : State<BookMarkState> = _state

    init {
        getArticle()
    }

    private fun getArticle(){
        newsUseCases.selectArticles().onEach {
            _state.value = _state.value.copy(article = it.reversed())
        }.launchIn(viewModelScope)
    }


}