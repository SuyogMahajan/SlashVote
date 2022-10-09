package com.slashvote.slashvote.data

data class Option(val text:String = "",val percentage: Float = 0f)
data class FirebaseOption(var option:String = "",var vote:Long = 0L)
data class FirebaseOptionList(var list:List<FirebaseOption> = listOf())