package com.example.listscreen

import android.app.appsearch.AppSearchBatchResult
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.contentValuesOf
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
            else -> {
                SearchBar(
                    uiState = uiState,
                    onSearchValueChange = {}
                )
            }
        }
    }
}

@Composable
internal fun SearchBar(
    uiState: ListState,
    onSearchValueChange: (input: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .clip(RoundedCornerShape(64.dp))
            .border(
                width = 1.dp,
                color = Color.Gray
            )
    ) {
        var searchText by rememberSaveable {
            mutableStateOf("")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = rememberVectorPainter(image = Icons.Default.Search),
                contentDescription = "search",
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .height(28.dp)
                    .padding(start = 16.dp)
            )
            TextField(
                singleLine = true,
                value = searchText,
                onValueChange = onSearchValueChange,
                placeholder = { Text(text = "Search") },
                modifier = Modifier
                    .weight(1f)
                    .scale(scaleX = 1f, scaleY = 0.8f)
                    .clip(RoundedCornerShape(0.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                ),
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(color = Color.Gray)
                    .padding(vertical = 2.dp)
            )
            Icon(
                painter = rememberVectorPainter(image = Icons.Default.LocationOn),
                contentDescription = "Current location"
            )
            Text(
                text = uiState.data?.location ?: "Current location",
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}