package com.android.news.presentation.onboarding

import androidx.annotation.DrawableRes
import com.android.news.R

data class Page(
    val title : String,
    val description :String,
    @DrawableRes val image : Int
)


val pages = listOf(
    Page(
        title = "Stay Informed Every Moment",
        description = "Discover the power of staying updated with real-time breaking news, trending topics, and important stories from trusted sources — all at your fingertips. Never miss a headline again.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Your News, Your Way",
        description = "Tailor your news experience by choosing your favorite categories — whether it's politics, technology, sports, or entertainment. Our smart recommendations adapt to your reading habits and preferences.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Seamless Access Anytime, Anywhere",
        description = "Catch up on the latest news while commuting, at work, or relaxing at home. With offline support and smooth performance, our app ensures you're always in the loop — wherever life takes you.",
        image = R.drawable.onboarding3
    )
)