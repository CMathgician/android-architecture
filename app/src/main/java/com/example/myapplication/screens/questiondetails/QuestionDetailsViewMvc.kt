package com.example.myapplication.screens.questiondetails

import com.example.myapplication.questions.QuestionDetails
import com.example.myapplication.screens.common.BaseViewMvc

interface QuestionDetailsViewMvc: BaseViewMvc {
    fun bindQuestion(question: QuestionDetails)
    fun showProgressIndication()
    fun hideProgressIndication()
}