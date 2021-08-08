package com.example.myapplication.questions

import com.example.myapplication.common.BaseObservable
import com.example.myapplication.networking.questions.QuestionDetailsResponseSchema
import com.example.myapplication.networking.questions.QuestionSchema
import com.example.myapplication.networking.StackoverflowApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchQuestionDetailsUseCase(private val stackoverflowApi: StackoverflowApi) :
    BaseObservable<FetchQuestionDetailsUseCase.Listener>() {

    interface Listener {
        fun onQuestionDetailsFetched(questionDetails: QuestionDetails)
        fun onQuestionDetailsFetchFailed()
    }

    fun fetchQuestionDetailsAndNotify(questionId: String) {
        stackoverflowApi.fetchQuestionDetails(questionId)
            .enqueue(object : Callback<QuestionDetailsResponseSchema> {
                override fun onResponse(
                    call: Call<QuestionDetailsResponseSchema>,
                    response: Response<QuestionDetailsResponseSchema>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { notifySuccess(it.question) }
                    } else {
                        notifyFailure()
                    }
                }

                override fun onFailure(call: Call<QuestionDetailsResponseSchema>, t: Throwable) {
                    notifyFailure()
                }
            })
    }

    private fun notifyFailure() {
        for (listener in getListeners()) {
            listener.onQuestionDetailsFetchFailed()
        }
    }

    private fun notifySuccess(question: QuestionSchema) {
        for (listener in getListeners()) {
            listener.onQuestionDetailsFetched(
                QuestionDetails(
                    question.questionId.toString(),
                    question.title.toString(),
                    question.body.toString()
                )
            )
        }
    }
}