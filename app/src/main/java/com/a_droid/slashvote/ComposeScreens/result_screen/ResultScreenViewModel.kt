package com.slashvote.slashvote.ComposeScreens.result_screen

import androidx.compose.runtime.mutableStateOf
import com.slashvote.slashvote.data.Option

class ResultScreenViewModel {
    val question = mutableStateOf(String())
    val option = mutableStateOf(arrayListOf<Option>())

}