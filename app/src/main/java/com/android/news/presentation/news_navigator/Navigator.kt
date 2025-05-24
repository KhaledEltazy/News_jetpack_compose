package com.android.news.presentation.news_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.news.R
import com.android.news.domain.model.Article
import com.android.news.presentation.bookmark.BookMarkScreen
import com.android.news.presentation.bookmark.BookMarkViewModel
import com.android.news.presentation.details.DetailsEvent
import com.android.news.presentation.details.DetailsScreen
import com.android.news.presentation.details.DetailsViewModel
import com.android.news.presentation.home.HomeScreen
import com.android.news.presentation.home.HomeViewModel
import com.android.news.presentation.navgraph.Route
import com.android.news.presentation.news_navigator.components.BottomNavigationItem
import com.android.news.presentation.news_navigator.components.NewsBottomNavigation
import com.android.news.presentation.search.SearchScreen
import com.android.news.presentation.search.SearchViewModel

@Composable
fun NewsNavigator() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "BookMark"),
        )
    }

    val navController = rememberNavController()

    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by remember {
        mutableStateOf(0)
    }

    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }

    val isBottomBarVisible = remember(key1 = backStackState){
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if(isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClicked = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Route.SearchScreen.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Route.BookmarkScreen.route
                            )
                        }
                    })
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ){
            composable(
                route = Route.HomeScreen.route
            ){
                val homeViewModel : HomeViewModel = hiltViewModel()
                val articles = homeViewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTab(navController = navController, route = Route.SearchScreen.route)
                    },
                    navigateToDetails = { article->
                        navigateToDetails(
                            navController = navController,
                            article = article)
                    }
                )
            }

            composable(
                route = Route.SearchScreen.route
            ){
                val searchViewModel : SearchViewModel = hiltViewModel()
                val state = searchViewModel.state.value
                SearchScreen(
                 state = state,
                    event = searchViewModel::onEvent,
                    navigateToDetails = {
                        navigateToDetails(navController = navController, article = it)
                    }
                )
            }

            composable(
                route = Route.DetailsScreen.route
            ){
                val detailsViewModel : DetailsViewModel = hiltViewModel()
                if (detailsViewModel.sideEffect != null){
                    Toast.makeText(LocalContext.current,detailsViewModel.sideEffect,Toast.LENGTH_LONG).show()
                    detailsViewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.
                get<Article>("article")?.let { article ->
                    DetailsScreen(
                        article = article,
                        event = detailsViewModel::onEvent,
                        navigateUp = {navController.navigateUp()}
                        )
                }
            }

            composable(
                route = Route.BookmarkScreen.route
            ){
                val searchViewModel : BookMarkViewModel = hiltViewModel()
                val state = searchViewModel.state.value
                BookMarkScreen(
                    state = state,
                    navigateToDetails = { article ->
                        navigateToDetails(navController = navController,
                            article = article)
                    }
                )
            }

        }
    }
}

fun navigateToTab(navController: NavController,route : String){
    navController.navigate(route){
        navController.graph.startDestinationRoute?.let { homeScreen->
            popUpTo(homeScreen){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(navController: NavController,article: Article){
    navController.currentBackStackEntry?.savedStateHandle?.set("article",article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}