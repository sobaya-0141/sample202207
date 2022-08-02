package com.example.initial_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun InitialScreenRoute(
    navigator: DestinationsNavigator,
    viewModel: InitialScreenViewModel = hiltViewModel()
) {
    InitialScreen(
        onClickCreateAccount = {},
        onClickSignIn = {}
    )
}

@Composable
internal fun InitialScreen(
    modifier: Modifier = Modifier,
    onClickCreateAccount: () -> Unit,
    onClickSignIn: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo(
            modifier = Modifier
                .padding(top = 32.dp)
                .padding(horizontal = 32.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(50.dp))
        ContentPager()
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 32.dp),
            onClick = onClickCreateAccount
        ) {
            Text(text = "Create a Free Account")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Or")
            TextButton(onClick = onClickSignIn) {
                Text(text = "Sign In")
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun ContentPager(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()
    val imageUrls = remember {
        listOf(
            "https://pbs.twimg.com/profile_images/1075242952287346688/qdFCE0yK_400x400.jpg",
            "https://pbs.twimg.com/profile_images/1075242952287346688/qdFCE0yK_400x400.jpg",
            "https://pbs.twimg.com/profile_images/1075242952287346688/qdFCE0yK_400x400.jpg"
        )
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            count = imageUrls.size,
            state = pagerState
        ) { page ->
            val imageUrl = imageUrls[page]
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            )
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(32.dp),
        )
    }
}

@Composable
fun Logo(
    modifier: Modifier = Modifier
) {
    AsyncImage(
        modifier = modifier,
        model = "https://pbs.twimg.com/profile_banners/1072657247388323840/1649466745/1500x500",
        contentDescription = "logo"
    )
}
