package com.example.myapplication.screens.questionslist

import com.example.myapplication.questions.Question
import com.example.myapplication.screens.common.ObservableViewMvc

interface QuestionsListViewMvc : ObservableViewMvc<QuestionsListViewMvc.Listener> {
    interface Listener {
        fun onQuestionClicked(question: Question)
    }

    fun bindQuestions(questions: List<Question>)
    fun showProgressIndication()
    fun hideProgressIndication()
}