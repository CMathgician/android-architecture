package com.example.myapplication.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import com.example.myapplication.R
import com.example.myapplication.questions.Question
import com.example.myapplication.screens.common.BaseObservableViewMvcImpl
import com.example.myapplication.screens.common.BaseViewMvcImpl
import com.example.myapplication.screens.questionslist.QuestionsListItemViewMvc.Listener

class QuestionsListItemViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?) :
    BaseObservableViewMvcImpl<Listener>(),
    QuestionsListItemViewMvc {

    private lateinit var question: Question
    private val txtTitle: TextView

    init {
        setRootView(inflater.inflate(R.layout.question_list_item, parent, false))
        txtTitle = findViewById(R.id.txt_title)
        getRootView().setOnClickListener {
            for (listener in getListeners()) {
                listener.onQuestionClicked(question)
            }
        }
    }

    override fun bindQuestion(question: Question) {
        this.question = question
        txtTitle.text = question.title
    }
}