package com.example.myapplication.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.questions.FetchQuestionDetailsUseCase
import com.example.myapplication.questions.QuestionDetails
import com.example.myapplication.screens.common.BaseActivity

class QuestionDetailsActivity : BaseActivity(), FetchQuestionDetailsUseCase.Listener {

    private lateinit var fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase

    private lateinit var viewMvc: QuestionDetailsViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fetchQuestionDetailsUseCase = getCompositionRoot().getFetchQuestionDetailsUseCase()
        viewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsViewMvc(null)

        setContentView(viewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        fetchQuestionDetailsUseCase.registerListener(this)
        viewMvc.showProgressIndication()
        fetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(getQuestionId())
    }

    override fun onStop() {
        super.onStop()
        fetchQuestionDetailsUseCase.unregisterListener(this)
    }

    private fun bindQuestionDetails(questionDetails: QuestionDetails) {
        viewMvc.hideProgressIndication()
        viewMvc.bindQuestion(questionDetails)
    }

    private fun getQuestionId(): String {
        return intent.getStringExtra(EXTRA_QUESTION_ID)!!
    }

    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

    override fun onQuestionDetailsFetched(questionDetails: QuestionDetails) {
        bindQuestionDetails(questionDetails)
    }

    override fun onQuestionDetailsFetchFailed() {
        viewMvc.hideProgressIndication()
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
    }
}