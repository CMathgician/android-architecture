package com.example.myapplication.screens.questionslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.questions.Question
import com.example.myapplication.screens.common.BaseObservableViewMvcImpl
import com.example.myapplication.screens.common.BaseViewMvcImpl
import com.example.myapplication.screens.questionslist.QuestionsListViewMvc.Listener

class QuestionsListViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?) : BaseObservableViewMvcImpl<Listener>(),
    QuestionsListViewMvc, QuestionsRecyclerAdapter.Listener {

    private var recyclerView: RecyclerView
    private var adapter: QuestionsRecyclerAdapter

    init {
        setRootView(inflater.inflate(R.layout.questions_list, parent, false))
        recyclerView = findViewById(R.id.recycler_questions)
        recyclerView.layoutManager = LinearLayoutManager(getContext())
        adapter = QuestionsRecyclerAdapter(inflater, this)
        recyclerView.adapter = adapter

    }

    override fun bindQuestions(questions: List<Question>) {
        adapter.bindQuestions(questions)
    }

    override fun onQuestionClicked(question: Question) {
        for (listener in getListeners()) {
            listener.onQuestionClicked(question)
        }
    }

}