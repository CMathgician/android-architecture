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

    private class ViewHolder {
        lateinit var txtTitle: TextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.question_list_item, parent, false)
            val viewHolder = ViewHolder()
            viewHolder.txtTitle = view.findViewById(R.id.txt_title)
            view.tag = viewHolder
        }

        val question: Question = getItem(position)!!

        // bind the data to views
        val viewHolder: ViewHolder = view?.tag as ViewHolder
        viewHolder.txtTitle.text = question.title

        // set listener
        view?.setOnClickListener { onQuestionClicked(question) }
        return view!!
    }

    private fun onQuestionClicked(question: Question) {
        onQuestionClickListener.onQuestionClicked(question)
    }
}