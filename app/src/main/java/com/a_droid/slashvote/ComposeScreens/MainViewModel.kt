package com.a_droid.slashvote.ComposeScreens

import com.slashvote.slashvote.ComposeScreens.choices_screen.ChoicesScreenViewModel
import com.slashvote.slashvote.ComposeScreens.otp_screen.OtpScreenViewModel
import com.slashvote.slashvote.ComposeScreens.question_screen.QuestionScreenViewModel
import com.slashvote.slashvote.ComposeScreens.result_screen.ResultScreenViewModel
import com.slashvote.slashvote.ComposeScreens.statics_screen.StaticScreenViewModel
import com.slashvote.slashvote.ComposeScreens.voting_screen.VotingScreenViewModel

class MainViewModel() {
    val question_screen_view_model = QuestionScreenViewModel()
    val choices_screen_view_model = ChoicesScreenViewModel()
    val statics_screen_view_model = StaticScreenViewModel()
    val otp_screen_view_model = OtpScreenViewModel()
    val voting_screen_view_model = VotingScreenViewModel()
    val result_screen_view_model = ResultScreenViewModel()


}