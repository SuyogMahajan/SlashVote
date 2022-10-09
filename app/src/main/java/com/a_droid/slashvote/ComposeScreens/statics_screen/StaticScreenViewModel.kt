package com.slashvote.slashvote.ComposeScreens.statics_screen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.slashvote.slashvote.data.Option

class StaticScreenViewModel(): ViewModel() {

    val Otp = mutableStateOf(0L)
    val question = mutableStateOf(String())
    val option = mutableStateOf(arrayListOf<Option>())

}