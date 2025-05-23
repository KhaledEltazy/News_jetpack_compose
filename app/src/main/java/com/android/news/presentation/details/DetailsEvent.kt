package com.android.news.presentation.details

import com.android.news.domain.model.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article : Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()

}