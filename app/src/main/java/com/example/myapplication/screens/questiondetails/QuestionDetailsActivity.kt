package com.example.myapplication.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.networking.QuestionDetailsResponseSchema
import com.example.myapplication.networking.QuestionSchema
import com.example.myapplication.networking.StackoverflowApi
import com.example.myapplication.questions.QuestionDetails
import com.example.myapplication.screens.common.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionDetailsActivity : BaseActivity() {

    private lateinit var stackoverflowApi: StackoverflowApi

    private lateinit var viewMvc: QuestionDetailsViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        stackoverflowApi = getCompositionRoot().getStackoverflowApi()
        viewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsViewMvc(null)

        setContentView(viewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        viewMvc.showProgressIndication()
        fetchQuestionDetails()
    }

    private fun fetchQuestionDetails() {
        stackoverflowApi.fetchQuestionDetails(getQuestionId())
            .enqueue(object : Callback<QuestionDetailsResponseSchema> {
                override fun onResponse(
                    call: Call<QuestionDetailsResponseSchema>,
                    response: Response<QuestionDetailsResponseSchema>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { bindQuestionDetails(it.question) }
                    } else {
                        networkCallFailed()
                    }
                }

                override fun onFailure(call: Call<QuestionDetailsResponseSchema>, t: Throwable) {
                    networkCallFailed()
                }
            })
    }

    private fun bindQuestionDetails(question: QuestionSchema) {
        viewMvc.hideProgressIndication()
        viewMvc.bindQuestion(
            QuestionDetails(
                question.questionId.toString(),
                question.title.toString(),
                question.body.toString()
            )
        )
    }

    private fun networkCallFailed() {
        viewMvc.hideProgressIndication()
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
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
}