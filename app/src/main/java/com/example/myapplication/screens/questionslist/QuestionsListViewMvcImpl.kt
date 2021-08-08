package com.example.myapplication.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.questions.Question
import com.example.myapplication.screens.common.BaseObservableViewMvcImpl
import com.example.myapplication.screens.common.ViewMvcFactory
import com.example.myapplication.screens.questionslist.QuestionsListViewMvc.Listener

class QuestionsListViewMvcImpl(
    inflater: LayoutInflater,
    parent: ViewGroup?,
    viewMvcFactory: ViewMvcFactory
) : BaseObservableViewMvcImpl<Listener>(),
    QuestionsListViewMvc, QuestionsRecyclerAdapter.Listener {

    private var recyclerView: RecyclerView
    private var progressBar: ProgressBar
    private var adapter: QuestionsRecyclerAdapter

    init {
        setRootView(inflater.inflate(R.layout.questions_list, parent, false))
        recyclerView = findViewById(R.id.recycler_questions)
        recyclerView.layoutManager = LinearLayoutManager(getContext())
        adapter = QuestionsRecyclerAdapter(this, viewMvcFactory)
        recyclerView.adapter = adapter
        progressBar = findViewById(R.id.progress_bar)
    }

    override fun bindQuestions(questions: List<Question>) {
        adapter.bindQuestions(questions)
    }

    override fun showProgressIndication() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressIndication() {
        progressBar.visibility = View.GONE
    }

    override fun onQuestionClicked(question: Question) {
        for (listener in getListeners()) {
            listener.onQuestionClicked(question)
        }
    }

}