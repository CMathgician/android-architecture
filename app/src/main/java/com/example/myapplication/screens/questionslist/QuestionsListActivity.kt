package com.example.myapplication.screens.questionslist

import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.questions.FetchLastActiveQuestionsUseCase
import com.example.myapplication.questions.Question
import com.example.myapplication.screens.common.BaseActivity
import com.example.myapplication.screens.questiondetails.QuestionDetailsActivity

class QuestionsListActivity : BaseActivity(), QuestionsListViewMvc.Listener,
    FetchLastActiveQuestionsUseCase.Listener {

    private lateinit var fetchLastActiveQuestionsUseCase: FetchLastActiveQuestionsUseCase

    private lateinit var viewMvc: QuestionsListViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fetchLastActiveQuestionsUseCase = getCompositionRoot().getFetchLastActiveQuestionsUseCase()
        viewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(null)
        viewMvc.registerListener(this)

        setContentView(viewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        fetchLastActiveQuestionsUseCase.registerListener(this)
        viewMvc.showProgressIndication()
        fetchLastActiveQuestionsUseCase.fetchLastActiveQuestionsAndNotify()
    }

    override fun onStop() {
        super.onStop()
        fetchLastActiveQuestionsUseCase.unregisterListener(this)
    }

    override fun onQuestionClicked(question: Question) {
        QuestionDetailsActivity.start(this, question.id)
    }

    override fun onLastActiveQuestionsFetched(questions: List<Question>) {
        viewMvc.hideProgressIndication()
        viewMvc.bindQuestions(questions)
    }

    override fun onLastActiveQuestionsFetchFailed() {
        viewMvc.hideProgressIndication()
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
    }
}