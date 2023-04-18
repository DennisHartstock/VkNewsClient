package com.commcode.vknewsclient.ui.theme

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.commcode.vknewsclient.domain.FeedPost
import com.commcode.vknewsclient.navigation.AppNavGraph
import com.commcode.vknewsclient.navigation.rememberNavigationState

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    val commentsToPost: MutableState<FeedPost?> = remember {
        mutableStateOf(null)
    }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorite,
                    NavigationItem.Profile
                )

                items.forEach { item ->
                    BottomNavigationItem(
                        selected = currentRoute == item.screen.route,
                        onClick = { navigationState.navigateTo(item.screen.route) },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        selectedContentColor = MaterialTheme.colors.onPrimary,
                        unselectedContentColor = MaterialTheme.colors.onSecondary
                    )
                }
            }
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            homeScreenContent = {
                if (commentsToPost.value == null) {
                    HomeScreen(
                        paddingValues = paddingValues,
                        onCommentClickListener = {
                            commentsToPost.value = it
                        }
                    )
                } else {
                    CommentsScreen(
                        onBackPressed = {
                            commentsToPost.value = null
                        },
                        feedPost = commentsToPost.value!!
                    )
                }
            },
            favoriteScreenContent = { /*TODO*/ },
            profileScreenContent = { /*TODO*/ }
        )
    }
}
