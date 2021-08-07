package com.example.myapplication.screens.questionslist

import com.example.myapplication.questions.Question
import com.example.myapplication.screens.common.ObservableViewMvc

interface QuestionsListItemViewMvc : ObservableViewMvc<QuestionsListItemViewMvc.Listener> {
    interface Listener {
        fun onQuestionClicked(question: Question)
    }

    fun bindQuestion(question: Question)
}