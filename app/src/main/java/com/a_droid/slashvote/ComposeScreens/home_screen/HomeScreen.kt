package com.slashvote.slashvote.ComposeScreens.home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.a_droid.slashvote.ui.theme.LightBlue
import com.a_droid.slashvote.ui.theme.LightGreen
import com.a_droid.slashvote.ui.theme.fontFamily

@Composable
fun HomeScreen(
    navController: NavController
) {

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {

            OptionsButton(
                title = "Conduct", modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(), colors = LightBlue,
                onClick = {

                }
            )

            OptionsButton(
                title = "Vote", modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(), colors = LightGreen, onClick = {

                       }
            )

        }

    }
}


@Composable
fun OptionsButton(onClick: () -> Unit, title: String, modifier: Modifier, colors: androidx.compose.ui.graphics.Color) {

    androidx.compose.material.Button(
        onClick = {
            onClick()
        },
        shape = CircleShape,
        modifier = Modifier
            .padding(5.dp)
            .then(modifier),
        colors = ButtonDefaults.buttonColors(colors), elevation = ButtonDefaults.elevation(10.dp)
    ) {
        Text(
            text = title, color = Color.White,
            style = MaterialTheme.typography.h4,
            fontFamily = fontFamily,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview1() {
//    ComposeApiAppTheme {
////       OptionsButton(title = "Conduct", colors = null)
////    HomeScreen(navController = NavController(this))
//    }
//}
