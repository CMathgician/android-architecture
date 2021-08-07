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
import com.example.myapplication.screens.questionslist.QuestionsListViewMvc.Listener

class QuestionsListsViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?) :
    QuestionsListViewMvc, QuestionsRecyclerAdapter.Listener {

    private val rootView: View
    private var recyclerView: RecyclerView
    private var adapter: QuestionsRecyclerAdapter
    private val listeners: HashSet<Listener> = HashSet(1)

    init {
        rootView = inflater.inflate(R.layout.questions_list, parent, false)
        recyclerView = findViewById(R.id.recycler_questions)
        recyclerView.layoutManager = LinearLayoutManager(getContext())
        adapter = QuestionsRecyclerAdapter(inflater, this)
        recyclerView.adapter = adapter

    }

    private fun getContext(): Context = rootView.context

    private fun <T : View?> findViewById(@IdRes id: Int): T = rootView.findViewById(id)

    override fun getRootView(): View = rootView

    override fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: Listener) {
        listeners.remove(listener)
    }

    override fun bindQuestions(questions: List<Question>) {
        adapter.bindQuestions(questions);
    }

    override fun onQuestionClicked(question: Question) {
        for (listener in listeners) {
            listener.onQuestionClicked(question)
        }
    }

}