package com.example.sample202207.menuscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController

@Composable
fun MenuScreeenRoute(
    navController: NavController
) {
    MenuScreen(
        onClickInitialScreen = { navController.navigate("InitialScreen") }
    )
}

@Composable
internal fun MenuScreen(
    modifier: Modifier = Modifier,
    onClickInitialScreen: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Cyan)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable {
                    onClickInitialScreen()
                }
        ) {
            Column(Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "InitialScreen",
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
ConstraintLayout {
    val (box, text) = createRefs()
    Box(modifier = Modifier
        .width(200.dp)
        .height(40.dp)
        .background(color = Color.Blue)
        .constrainAs(box) {
            start.linkTo(parent.end)
            end.linkTo(parent.start)
        }
    ) {}
    Text(
        modifier = Modifier
            .background(Color.Red)
            .constrainAs(text) {
                start.linkTo(box.start)
                end.linkTo(box.end)
                top.linkTo(box.bottom)
                width = Dimension.fillToConstraints
            },
        text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    )
}
    }
}
