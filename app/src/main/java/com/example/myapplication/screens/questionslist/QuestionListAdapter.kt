package com.example.myapplication.screens.questionslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.myapplication.questions.Question

class QuestionsListAdapter(
    context: Context,
    private val onQuestionClickListener: OnQuestionClickListener
) : ArrayAdapter<Question>(context, 0), QuestionListItemViewMvc.Listener {
    interface OnQuestionClickListener {
        fun onQuestionClicked(question: Question)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            val viewMvc: QuestionListItemViewMvc =
                QuestionListItemViewMvcImpl(LayoutInflater.from(context), parent)
            viewMvc.registerListener(this)
            view = viewMvc.getRootView()
            view.tag = viewMvc
        }

        val question: Question = getItem(position)!!

        val viewMvc = view.tag as QuestionListItemViewMvc
        viewMvc.bindQuestion(question)

        // set listener
        view.setOnClickListener { onQuestionClicked(question) }
        return view
    }

    override fun onQuestionClicked(question: Question) {
        onQuestionClickListener.onQuestionClicked(question)
    }
}