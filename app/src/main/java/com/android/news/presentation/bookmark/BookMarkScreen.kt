package com.android.news.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.android.news.R
import com.android.news.presentation.common.ArticlesList
import com.android.news.presentation.navgraph.Route
import com.android.news.ui.theme.unit.Dimens.MediumPadding1

@Composable
fun BookMarkScreen(
    state: BookMarkState,
    navigate : (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = MediumPadding1, start = MediumPadding1,end= MediumPadding1)
    ) {
        Text (
           text = "BookMark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(R.color.text_title)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticlesList(articles = state.article, onClick = {navigate(Route.DetailsScreen.route)})
    }
}