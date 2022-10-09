package com.slashvote.slashvote.ComposeScreens.choices_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

class ChoicesScreenViewModel() {

    lateinit var question:String
    val OptionsList =  mutableStateListOf<String>()

}