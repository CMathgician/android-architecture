package com.example.myapplication.screens.questionslist

import android.view.View
import com.example.myapplication.questions.Question

interface QuestionsListItemViewMvc {
    interface Listener {
        fun onQuestionClicked(question: Question)
    }

    fun getRootView(): View
    fun registerListener(listener: Listener)
    fun unregisterListener(listener: Listener)
    fun bindQuestion(question: Question)
}