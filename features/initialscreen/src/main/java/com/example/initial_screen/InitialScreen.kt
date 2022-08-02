package com.example.initial_screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
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
    InitialScreen()
}

@Composable
fun InitialScreen(
    modifier: Modifier = Modifier
) {
    Text("TEST")
}
