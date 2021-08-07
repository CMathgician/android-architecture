package com.example.myapplication.screens.questionslist

import android.view.View
import com.example.myapplication.questions.Question
import com.example.myapplication.screens.common.BaseViewMvc

interface QuestionsListItemViewMvc: BaseViewMvc {
    interface Listener {
        fun onQuestionClicked(question: Question)
    }

    fun registerListener(listener: Listener)
    fun unregisterListener(listener: Listener)
    fun bindQuestion(question: Question)
}