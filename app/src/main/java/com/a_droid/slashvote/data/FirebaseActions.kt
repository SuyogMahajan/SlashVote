package com.slashvote.slashvote.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.a_droid.slashvote.ComposeScreens.MainViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

val firebaseDataBase by lazy {
    FirebaseDatabase.getInstance()
}

fun PostQuestion(question: String, choiceList: List<String>,viewModel: MainViewModel) {

    val otp = firebaseDataBase.reference.child("vote_bank_count")

    otp.get().addOnSuccessListener {


    }.addOnFailureListener {

    }
}

fun vote(otp: Long, index: Long) {

}
