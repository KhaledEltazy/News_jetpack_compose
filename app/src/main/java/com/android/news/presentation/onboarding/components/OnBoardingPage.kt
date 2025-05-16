package com.android.news.presentation.onboarding.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.news.R
import com.android.news.ui.theme.unit.Dimens.MediumPadding1
import com.android.news.ui.theme.unit.Dimens.MediumPadding2
import com.android.news.presentation.onboarding.Page
import com.android.news.presentation.onboarding.pages
import com.android.news.ui.theme.NewsTheme

@Composable
fun OnBoardingPage(
    modifier : Modifier = Modifier,
    page: Page
){
    Box(modifier = Modifier.fillMaxSize()) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        var showFullText by remember {
            mutableStateOf(false)
        }

        Image(
            painter = painterResource(page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.6f)
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = MediumPadding2)
        ) {
            Text(
                text = page.title,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(R.color.display_small)
            )
            Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = page.description,
                    textAlign = TextAlign.Start,
                    fontSize = 26.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(R.color.text_medium),
                    maxLines = if (showFullText) Int.MAX_VALUE else 2,
                    overflow = if (showFullText) TextOverflow.Visible else TextOverflow.Ellipsis,
                    modifier = Modifier.clickable {
                        showFullText = !showFullText
                    }
                )

        }
    }

        }
    }

@Preview(name = "Light", showBackground = true)
@Preview(name = "Night", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewOnBoarding(){
    NewsTheme {
        OnBoardingPage(page = pages[0])
    }

}