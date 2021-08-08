package com.example.myapplication.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.screens.questiondetails.QuestionDetailsViewMvc
import com.example.myapplication.screens.questiondetails.QuestionDetailsViewMvcImpl
import com.example.myapplication.screens.questionslist.questionslistitem.QuestionsListItemViewMvc
import com.example.myapplication.screens.questionslist.questionslistitem.QuestionsListItemViewMvcImpl
import com.example.myapplication.screens.questionslist.QuestionsListViewMvc
import com.example.myapplication.screens.questionslist.QuestionsListViewMvcImpl

class ViewMvcFactory(private val layoutInflater: LayoutInflater) {

    fun getQuestionsListViewMvc(parent: ViewGroup?): QuestionsListViewMvc {
        return QuestionsListViewMvcImpl(layoutInflater, parent, this)
    }

    fun getQuestionListItemViewMvc(parent: ViewGroup?): QuestionsListItemViewMvc {
        return QuestionsListItemViewMvcImpl(layoutInflater, parent)
    }

    fun getQuestionDetailsViewMvc(parent: ViewGroup?): QuestionDetailsViewMvc {
        return QuestionDetailsViewMvcImpl(layoutInflater, parent)
    }
}