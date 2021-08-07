package com.example.myapplication.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import com.example.myapplication.R
import com.example.myapplication.questions.Question
import com.example.myapplication.screens.questionslist.QuestionListItemViewMvc.Listener

class QuestionListItemViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?) :
    QuestionListItemViewMvc {

    private val rootView: View
    private val listeners: HashSet<Listener> = HashSet(1)
    private lateinit var question: Question
    private val txtTitle: TextView

    init {
        rootView = inflater.inflate(R.layout.question_list_item, parent, false)
        txtTitle = findViewById(R.id.txt_title)
        getRootView().setOnClickListener {
            for (listener in listeners) {
                listener.onQuestionClicked(question)
            }
        }


        getRootView().setOnClickListener {
            for (listener in listeners) {
                listener.onQuestionClicked(question)
            }
        }
    }

    private fun <T : View?> findViewById(@IdRes id: Int): T = getRootView().findViewById(id)

    override fun getRootView(): View = rootView

    override fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: Listener) {
        listeners.remove(listener)
    }

    override fun bindQuestion(question: Question) {
        this.question = question
        txtTitle.text = question.title
    }
}