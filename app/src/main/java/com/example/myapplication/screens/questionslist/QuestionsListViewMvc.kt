package com.example.myapplication.screens.questionslist

import android.view.View
import com.example.myapplication.questions.Question
import com.example.myapplication.screens.common.BaseViewMvc

interface QuestionsListViewMvc: BaseViewMvc {
    interface Listener {
        fun onQuestionClicked(clickedQuestion: Question)
    }

    fun registerListener(listener: Listener)
    fun unregisterListener(listener: Listener)
    fun bindQuestions(questions: List<Question>)
}