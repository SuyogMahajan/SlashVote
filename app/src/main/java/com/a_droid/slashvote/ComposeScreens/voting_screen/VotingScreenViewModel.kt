package com.slashvote.slashvote.ComposeScreens.voting_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.slashvote.slashvote.data.Option

class VotingScreenViewModel(): ViewModel() {

    val question = mutableStateOf(String())
    val option = mutableStateOf(HashMap<String,Option>())
    var selected_item_key = mutableStateOf(String())

}
