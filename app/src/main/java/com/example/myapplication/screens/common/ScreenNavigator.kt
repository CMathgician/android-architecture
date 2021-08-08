package com.example.myapplication.screens.common

import android.content.Context
import com.example.myapplication.screens.questiondetails.QuestionDetailsActivity

class ScreenNavigator(private val context: Context) {
    fun toDialogDetails(questionId: String) {
        QuestionDetailsActivity.start(context, questionId)
    }
}