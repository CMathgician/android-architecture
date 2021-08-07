package com.example.myapplication.screens.questionslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.questions.Question

class QuestionsListAdapter(
    context: Context,
    private val onQuestionClickListener: OnQuestionClickListener
) : ArrayAdapter<Question>(context, 0) {
    interface OnQuestionClickListener {
        fun onQuestionClicked(question: Question)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.question_list_item, parent, false)
        }

        val question: Question = getItem(position)!!

        // bind the data to views
        val txtTitle = view!!.findViewById<TextView>(R.id.txt_title)
        txtTitle.text = question.title

        // set listener
        view.setOnClickListener { onQuestionClicked(question) }
        return view
    }

    private fun onQuestionClicked(question: Question) {
        onQuestionClickListener.onQuestionClicked(question)
    }
}