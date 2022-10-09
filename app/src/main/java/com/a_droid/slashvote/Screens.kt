package com.a_droid.slashvote

sealed class Screens(val route:String){

object HomeScreen : Screens("home_screen")
object QuestionScreen : Screens("question_screen")
object ChoicesScreen : Screens("choices_screen")
object StaticsScreen : Screens("statics_screen")
object OtpScreen : Screens("otp_screen")
object VotingScreen : Screens("voting_screen")
object ResultScreen : Screens("result_screen")

}