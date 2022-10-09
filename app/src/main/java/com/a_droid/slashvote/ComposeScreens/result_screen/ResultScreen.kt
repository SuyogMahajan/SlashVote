package com.slashvote.slashvote.ComposeScreens.result_screen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
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
import com.a_droid.slashvote.ui.theme.Orange
import com.a_droid.slashvote.ui.theme.fontFamily
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.slashvote.slashvote.ComposeScreens.statics_screen.VotePanel
import com.slashvote.slashvote.data.FirebaseOption
import com.slashvote.slashvote.data.Option
import com.slashvote.slashvote.data.firebaseDataBase

@Composable
fun ResultScreen(navController: NavController,viewModel: MainViewModel) {

    val text = viewModel.otp_screen_view_model.OTP.value
    var question by remember {
        viewModel.result_screen_view_model.question
    }
    var total_votes: Long? by remember {
        mutableStateOf(0L)
    }
    var list by remember {
        viewModel.result_screen_view_model.option
    }

BackHandler() {
    navController.popBackStack(route = Screens.HomeScreen.route,inclusive = false)
}

Box(modifier = Modifier.fillMaxSize()) {
    Column(modifier = Modifier.padding(10.dp).align(Alignment.Center)) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp),
            text = "Live Results",
            color = Color.Gray,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = fontFamily,
            textAlign = TextAlign.Start
        )

        val data = firebaseDataBase.reference.child("vote_banks").child(text.toString())

        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

                when (snapshot.key) {
                    "Total Votes" -> total_votes = snapshot.value as Long?
                    "question" -> question = snapshot.value as String? ?: "getting NUll"
                    "Choices" -> {
                        val list1 = arrayListOf<Option>()

                        for (dataSnap in snapshot.children) {

                            val c = dataSnap.getValue(FirebaseOption::class.java)

                            var f = try {
                                c!!.vote / total_votes!!.toFloat()
                            } catch (e: Exception) {
                                0f
                            }
                            if (f.isNaN()) f = 0f
                            list1.add(Option(c!!.option, f))
                        }
                        list = list1
                    }
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

                when (snapshot.key) {
                    "Total Votes" -> {
                        total_votes = snapshot.value as Long?
                        Log.d("DATA!!", total_votes.toString())
                    }
                    "Choices" -> {
                        val list1 = arrayListOf<Option>()

                        for (dataSnap in snapshot.children) {

                            val c = dataSnap.getValue(FirebaseOption::class.java)
                            var f: Float = try {
                                c!!.vote / total_votes!!.toFloat()
                            } catch (e: Exception) {
                                Log.d("DATA!!", e.message.toString())
                                0f
                            }
                            if (f.isNaN()) f = 0f

                            list1.add(Option(c!!.option, f))
                        }
                        list = list1
                        Log.d("DATA!!", list1.toString())
                    }
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

        }

        data.addChildEventListener(childEventListener)

        VotePanel(
            statics = true, question = question, options = list
        )

    }

    Button(
        onClick = {
            navController.popBackStack(route = Screens.HomeScreen.route,inclusive = false)
        },
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(vertical = 20.dp),
        shape = RoundedCornerShape(20.dp), colors = ButtonDefaults.buttonColors(Orange)
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            color = Color.White,
            text = "Home",
            fontFamily = fontFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )
    }
}
}


