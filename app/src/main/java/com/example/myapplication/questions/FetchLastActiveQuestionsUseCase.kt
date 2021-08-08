package com.example.myapplication.questions

import com.example.myapplication.common.BaseObservable
import com.example.myapplication.common.Constants
import com.example.myapplication.networking.questions.QuestionSchema
import com.example.myapplication.networking.questions.QuestionsListResponseSchema
import com.example.myapplication.networking.StackoverflowApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FetchLastActiveQuestionsUseCase(private val stackoverflowApi: StackoverflowApi) :
    BaseObservable<FetchLastActiveQuestionsUseCase.Listener>() {

    interface Listener {
        fun onLastActiveQuestionsFetched(questions: List<Question>)
        fun onLastActiveQuestionsFetchFailed()
    }

    fun fetchLastActiveQuestionsAndNotify() {
        stackoverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
            .enqueue(object : Callback<QuestionsListResponseSchema> {
                override fun onResponse(
                    call: Call<QuestionsListResponseSchema>,
                    response: Response<QuestionsListResponseSchema>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { notifySuccess(it.questions) }
                    } else {
                        notifyFailure()
                    }
                }

                override fun onFailure(call: Call<QuestionsListResponseSchema>, t: Throwable) {
                    notifyFailure()
                }
            })
    }

    private fun notifySuccess(questionSchemas: List<QuestionSchema>) {
        val questions: MutableList<Question> = ArrayList<Question>(questionSchemas.size)
        for (questionSchema in questionSchemas) {
            questions.add(
                Question(
                    questionSchema.questionId.toString(),
                    questionSchema.title.toString()
                )
            )
        }

        for (listener in getListeners()) {
            listener.onLastActiveQuestionsFetched(questions)
        }
    }

    private fun notifyFailure() {
        for (listener in getListeners()) {
            listener.onLastActiveQuestionsFetchFailed()
        }
    }
}