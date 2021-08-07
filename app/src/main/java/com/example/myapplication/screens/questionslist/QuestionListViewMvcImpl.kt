package com.example.myapplication.screens.questionslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.annotation.IdRes
import com.example.myapplication.R
import com.example.myapplication.questions.Question
import com.example.myapplication.screens.questionslist.QuestionListViewMvc.Listener
import kotlin.collections.HashSet

class QuestionListViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?) :
    QuestionsListAdapter.OnQuestionClickListener, QuestionListViewMvc {

    private val rootView: View
    private var listView: ListView
    private var questionsListAdapter: QuestionsListAdapter
    private val listeners: HashSet<Listener> = HashSet(1)

    init {
        rootView = inflater.inflate(R.layout.questions_list, parent, false)
        listView = findViewById(R.id.list_view_questions)
        questionsListAdapter = QuestionsListAdapter(getContext(), this)
        listView.adapter = questionsListAdapter

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
        questionsListAdapter.clear()
        questionsListAdapter.addAll(questions)
        questionsListAdapter.notifyDataSetChanged()
    }

    override fun onQuestionClicked(question: Question) {
        for (listener in listeners) {
            listener.onQuestionClicked(question)
        }
    }

}