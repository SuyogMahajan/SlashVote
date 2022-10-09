package com.slashvote.slashvote.ComposeScreens.otp_screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.slashvote.R
import com.slashvote.slashvote.data.FirebaseOption
import com.slashvote.slashvote.data.Option
import com.slashvote.slashvote.data.firebaseDataBase

@Composable
fun OtpScreen(viewModel: MainViewModel, navController: NavController) {
    val otp = viewModel.otp_screen_view_model.OTP


    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
        ) {

            Text(
                text = "Enter OTP ,\n to join Voting.",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = fontFamily,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            var text by remember {
                mutableStateOf("")
            }

            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp, vertical = 5.dp).align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(100.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(20.dp))
            val context = LocalContext.current
            Button(
                onClick = {
                    val Otp = text.toLong()

                    firebaseDataBase.reference.child("vote_banks")
                        .child(Otp.toString()).get().addOnSuccessListener {

                            if(it.value == null) {
                                 Toast.makeText(context,"Please Enter valid OTP.",Toast.LENGTH_LONG).show()
                            }else {
                                val options = it.child("Choices")
                                val list = HashMap<String,Option>()

                                for (dataSnap in options.children) {
                                    val c = dataSnap.getValue(FirebaseOption::class.java)
                                    list.put(dataSnap.key.toString(),Option(c!!.option, 0f))
                                }

                                navController.navigate(Screens.VotingScreen.route)
                            }
                        }.addOnFailureListener {
                            Toast.makeText(context,"Something went wrong, Please try again !",Toast.LENGTH_LONG).show()
                        }

                },
                modifier = Modifier
                    .padding(10.dp),
                shape = RoundedCornerShape(20.dp), colors = ButtonDefaults.buttonColors(LightBlue)
            ) {
                Text(
                    text = "Proceed", fontFamily = fontFamily,
                    fontWeight = FontWeight.ExtraBold, color = Color.White, fontSize = 20.sp
                )
            }

            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.question_sticker),
                contentDescription = "Gif"
            )

        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview2() {
//    ComposeApiAppTheme {
//        OtpScreen(MainViewModel(), rememberNavController())
//    }
//}
