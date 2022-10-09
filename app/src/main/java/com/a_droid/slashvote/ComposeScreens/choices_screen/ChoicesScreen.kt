package com.slashvote.slashvote.ComposeScreens.choices_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.a_droid.slashvote.ComposeScreens.MainViewModel
import com.a_droid.slashvote.Screens
import com.a_droid.slashvote.ui.theme.LightBlue
import com.a_droid.slashvote.ui.theme.fontFamily
import com.a_droid.slashvote.R
import com.slashvote.slashvote.ComposeScreens.models.PercentageBar
import com.slashvote.slashvote.data.PostQuestion

var ID:Long = 0L

@Composable
fun ChoicesScreen(
    viewModel: MainViewModel, navController: NavController
) {

    val ChoiceList = viewModel.choices_screen_view_model.OptionsList
    val question = viewModel.choices_screen_view_model.question
    var text by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(20.dp),
                text = question,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = fontFamily,
                textAlign = TextAlign.Center
            )

            LazyColumn(modifier = Modifier.padding(horizontal = 15.dp)) {
                items(ChoiceList.size) {
                    PercentageBar(text = ChoiceList[it], percentage = 0f)
                }
            }



            Box() {

                TextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    modifier = Modifier
                        .padding(bottom = 17.dp)
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(end = 40.dp)
                        .clip(CircleShape)
                        .clickable {

                            ChoiceList.add(text)
                            text = ""

                        }
                        .background(LightBlue)
                        .padding(10.dp)
                        .align(Alignment.BottomEnd)

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "Add",
                        modifier = Modifier.size(35.dp)
                    )
                }

            }
        }

        Button(
            onClick = {
                text = ""
                PostQuestion(question, ChoiceList,viewModel)
                navController.navigate(Screens.StaticsScreen.route)

            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 20.dp),
            shape = RoundedCornerShape(20.dp), colors = ButtonDefaults.buttonColors(LightBlue)
        ) {
            Text(
                modifier = Modifier.padding(5.dp), text = "Post", fontFamily = fontFamily,
                fontWeight = FontWeight.ExtraBold, color = Color.White, fontSize = 18.sp
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview3() {
//    ComposeApiAppTheme {
//        ChoicesScreen(MainViewModel(), rememberNavController())
//    }
//}
