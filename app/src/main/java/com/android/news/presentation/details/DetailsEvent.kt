package com.android.news.presentation.details

sealed class DetailsEvent {
    object SaveArticle : DetailsEvent()
}