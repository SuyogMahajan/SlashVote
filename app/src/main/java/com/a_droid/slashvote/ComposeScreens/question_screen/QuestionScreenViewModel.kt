package com.slashvote.slashvote.ComposeScreens.question_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class QuestionScreenViewModel():ViewModel() {
   val question = mutableStateOf(String())
}
