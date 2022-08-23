package com.example.listscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ListScreenRoute(
    navController: NavController,
    viewModel: ListViewModel
) {
    ListScreen(
        viewModel.uiState,
        onClickRetry = viewModel::getListData
    )
}

@Composable
internal fun ListScreen(
    uiState: ListState,
    onClickRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        when {
            uiState.isError -> {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("エラーです")
                        Button(onClick = onClickRetry) {
                            Text("もう一回")
                        }
                    }
                }
            }
            uiState.isLoading -> {
                Text("loading")
            }
            else -> {
                Text(text = "SUCCESS")
            }
        }
    }
}