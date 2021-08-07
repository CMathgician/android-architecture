package com.example.myapplication.screens.questionslist

import android.view.View
import com.example.myapplication.questions.Question

interface QuestionsListViewMvc {
    interface Listener {
        fun onQuestionClicked(clickedQuestion: Question)
    }

    fun getRootView(): View
    fun registerListener(listener: Listener)
    fun unregisterListener(listener: Listener)
    fun bindQuestions(questions: List<Question>)
}