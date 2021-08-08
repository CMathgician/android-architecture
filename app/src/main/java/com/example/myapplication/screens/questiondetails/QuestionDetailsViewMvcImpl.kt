package com.example.myapplication.screens.questiondetails

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.questions.QuestionDetails
import com.example.myapplication.screens.common.BaseViewMvcImpl

class QuestionDetailsViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?) : BaseViewMvcImpl(),
    QuestionDetailsViewMvc {

    private var txtQuestionTitle: TextView
    private var txtQuestionBody: TextView
    private var progressBar: ProgressBar

    init {
        setRootView(inflater.inflate(R.layout.question_details, parent, false))

        txtQuestionTitle = findViewById(R.id.txt_question_title)
        txtQuestionBody = findViewById(R.id.txt_question_body)
        progressBar = findViewById(R.id.progress_bar)
    }

    override fun bindQuestion(question: QuestionDetails) {
        txtQuestionTitle.text = Html.fromHtml(question.title, Html.FROM_HTML_MODE_LEGACY)
        txtQuestionBody.text = Html.fromHtml(question.body, Html.FROM_HTML_MODE_LEGACY)
    }

    override fun showProgressIndication() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressIndication() {
        progressBar.visibility = View.GONE
    }
}