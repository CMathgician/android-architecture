package com.example.myapplication.screens.questionslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.questions.Question

class QuestionsRecyclerAdapter(
    private val inflater: LayoutInflater,
    private val listener: Listener
) :
    RecyclerView.Adapter<QuestionsRecyclerAdapter.MyViewHolder>(),
    QuestionsListItemViewMvc.Listener {

    private var questions: List<Question> = listOf()

    interface Listener {
        fun onQuestionClicked(question: Question)
    }

    class MyViewHolder(val viewMvc: QuestionsListItemViewMvc) :
        RecyclerView.ViewHolder(viewMvc.getRootView()) {
    }

    fun bindQuestions(questions: List<Question>) {
        this.questions = questions
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewMvc: QuestionsListItemViewMvc = QuestionsListItemViewMvcImpl(inflater, parent)
        viewMvc.registerListener(this)
        return MyViewHolder(viewMvc)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewMvc.bindQuestion(questions[position])
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onQuestionClicked(question: Question) {
        listener.onQuestionClicked(question)
    }
}