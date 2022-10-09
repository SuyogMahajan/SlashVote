package com.a_droid.slashvote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.a_droid.slashvote.ComposeScreens.MainViewModel
import com.a_droid.slashvote.ui.theme.SlashVoteTheme
import com.slashvote.slashvote.ComposeScreens.home_screen.HomeScreen
import com.slashvote.slashvote.ComposeScreens.otp_screen.OtpScreen
import com.slashvote.slashvote.ComposeScreens.question_screen.QuestionScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlashVoteTheme {
                val navController = rememberNavController()
                val mainViewModel = MainViewModel()

                NavHost(
                    navController = navController,
                    startDestination = Screens.HomeScreen.route
                ) {

                    composable(Screens.HomeScreen.route) {
                        HomeScreen(navController = navController)
                    }

                    composable(
                        Screens.QuestionScreen.route
                    ) {
                        QuestionScreen(viewModel = mainViewModel, navController = navController)
                    }

                    composable(
                        Screens.OtpScreen.route
                    ) {
                        OtpScreen(viewModel = mainViewModel, navController = navController)
                    }

                }


            }
        }
    }
}
