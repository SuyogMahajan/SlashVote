package com.slashvote.slashvote.ComposeScreens.voting_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.a_droid.slashvote.ComposeScreens.MainViewModel
import com.a_droid.slashvote.Screens
import com.a_droid.slashvote.ui.theme.LightBlue
import com.a_droid.slashvote.ui.theme.MudyWhite
import com.a_droid.slashvote.ui.theme.fontFamily
import com.slashvote.slashvote.data.Option
import com.slashvote.slashvote.data.firebaseDataBase
import com.slashvote.slashvote.data.pair

@Composable
fun VotingScreen(viewModel: MainViewModel, navController: NavController) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp),
                text = "Vote for One Option !!!",
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = fontFamily,
                textAlign = TextAlign.Start
            )


            VoteOptions(
                question = viewModel.voting_screen_view_model.question.value,
                options = viewModel.voting_screen_view_model.option.value,
                viewModel = viewModel
            )
        }

        Button(
            onClick = {

                val path = firebaseDataBase.reference.child("vote_banks").child(viewModel.otp_screen_view_model.OTP.value.toString())

                Log.d("TotalVotes",viewModel.otp_screen_view_model.OTP.toString())

                path.child("Total Votes").get().addOnSuccessListener {
                    Log.d("TotalVotes",it.value.toString())

                  if(it.value != null) {
                      val v = (it.value as Long) + 1L
                      path.child("Total Votes").setValue(v)
                  }
                }

                path.child("Choices").child(viewModel.voting_screen_view_model.selected_item_key.value).child("vote").get().addOnSuccessListener {
                    if(it.value != null) {
                        val v = (it.value as Long) + 1L
                        path.child("Choices").child(viewModel.voting_screen_view_model.selected_item_key.value).child("vote").setValue(v)
                    }
                }

                navController.navigate(Screens.ResultScreen.route)

            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 20.dp),
            shape = RoundedCornerShape(20.dp), colors = ButtonDefaults.buttonColors(LightBlue)
        ) {
            Text(
                modifier = Modifier.padding(5.dp), text = "Confirm", fontFamily = fontFamily,
                fontWeight = FontWeight.ExtraBold, color = Color.White, fontSize = 18.sp
            )
        }
    }
}

@Composable
fun VoteOptions(
    modifier: Modifier = Modifier,
    question: String,
    options: HashMap<String, Option>,
    viewModel:MainViewModel
) {

    var selected_index by remember {
        mutableStateOf(-1)
    }

    var selected_item_key = viewModel.voting_screen_view_model.selected_item_key

    val list = options.map {
        pair(it.key,it.value)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(LightBlue, RoundedCornerShape(20.dp))
            .then(modifier)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.padding(10.dp),
                text = question,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = fontFamily,
                textAlign = TextAlign.Center
            )

            LazyColumn {
                items(list.size) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .border( width = 0.dp,
                                color = Color.Transparent, shape = RoundedCornerShape(20.dp))
                            .background(if(selected_index == it) Color.Gray else MudyWhite)
                            .clickable {
                              selected_index = it
                              selected_item_key.value = list[it].key
                            }
                    ){
                        Text(
                            text = list[it].option.text,
                            modifier = Modifier
                                .padding(start = 20.dp, top = 15.dp, bottom = 15.dp)
                                .align(Alignment.CenterStart)
                        )
                    }
                }
            }
        }
    }
}

