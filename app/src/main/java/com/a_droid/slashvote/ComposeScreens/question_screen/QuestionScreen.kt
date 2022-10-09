package com.slashvote.slashvote.ComposeScreens.question_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.a_droid.slashvote.ComposeScreens.MainViewModel
import com.a_droid.slashvote.Screens
import com.a_droid.slashvote.ui.theme.LightBlue
import com.a_droid.slashvote.ui.theme.fontFamily

@Composable
fun QuestionScreen(viewModel: MainViewModel, navController: NavController) {

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var text by remember {
            mutableStateOf(String())
        }

        Text(
            text = "Please Enter the Question here.",
            modifier = Modifier
                .padding(10.dp),
            textAlign = TextAlign.Center, fontFamily = fontFamily,
            fontWeight = FontWeight.Bold, color = Color.Black,
            fontSize = 18.sp
        )

        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .weight(10f),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        Button(
            onClick = {
               if(text.isNotEmpty()) {
                   text = ""
                   navController.navigate(Screens.ChoicesScreen.route)
               }else{
                   }
            },
            modifier = Modifier
                .weight(1f)
                .padding(10.dp),
            shape = RoundedCornerShape(20.dp), colors = ButtonDefaults.buttonColors(LightBlue)
        ) {
            Text(
                text = "Proceed", fontFamily = fontFamily,
                fontWeight = FontWeight.ExtraBold, color = Color.White, fontSize = 18.sp
            )
        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview3() {
//    ComposeApiAppTheme {
//        QuestionScreen(MainViewModel(), navController = rememberNavController())
//    }
//}
//
