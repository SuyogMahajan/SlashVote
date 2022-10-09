package com.slashvote.slashvote.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.FirebaseDatabase
import com.slashvote.slashvote.ComposeScreens.MainViewModel
import com.slashvote.slashvote.ComposeScreens.choices_screen.ID
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception

val firebaseDataBase by lazy {
    FirebaseDatabase.getInstance()
}

fun PostQuestion(question: String, choiceList: List<String>,viewModel: MainViewModel) {

    val otp = firebaseDataBase.reference.child("vote_bank_count")

    otp.get().addOnSuccessListener {
        val id = it.value as Long + 1L

        viewModel.statics_screen_view_model.Otp.value = id

        ID = id
        otp.setValue(id)

        val path = firebaseDataBase.reference
            .child("vote_banks")
            .child(id.toString())

        path.child("question")
            .setValue(question)

        path.child("Total Votes")
            .setValue(0L)

        choiceList.forEach {

            val flag = path.child("Choices").push()
            path.child("Choices").child(flag.key.toString()).child("option").setValue(it)
            path.child("Choices").child(flag.key.toString()).child("vote").setValue(0L)

        }

    }.addOnFailureListener {

    }
}

fun vote(otp: Long, index: Long) {

}
