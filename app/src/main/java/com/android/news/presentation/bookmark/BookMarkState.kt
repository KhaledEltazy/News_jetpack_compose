package com.android.news.presentation.bookmark

import com.android.news.domain.model.Article

data class BookMarkState (
    val article : List<Article> = emptyList()
)