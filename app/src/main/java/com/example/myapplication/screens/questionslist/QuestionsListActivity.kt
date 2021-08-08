package com.example.myapplication.screens.questionslist

import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.common.Constants
import com.example.myapplication.networking.QuestionSchema
import com.example.myapplication.networking.QuestionsListResponseSchema
import com.example.myapplication.networking.StackoverflowApi
import com.example.myapplication.questions.Question
import com.example.myapplication.screens.common.BaseActivity
import com.example.myapplication.screens.questiondetails.QuestionDetailsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class QuestionsListActivity : BaseActivity(), QuestionsListViewMvc.Listener {

    private lateinit var stackoverflowApi: StackoverflowApi

    private lateinit var viewMvc: QuestionsListViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        stackoverflowApi = getCompositionRoot().getStackoverflowApi()
        viewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(null)
        viewMvc.registerListener(this)

        setContentView(viewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        fetchQuestions()
    }

    private fun fetchQuestions() {
        stackoverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
            .enqueue(object : Callback<QuestionsListResponseSchema> {
                override fun onResponse(
                    call: Call<QuestionsListResponseSchema>,
                    response: Response<QuestionsListResponseSchema>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { bindQuestions(it.questions) }
                    } else {
                        networkCallFailed()
                    }
                }

                override fun onFailure(call: Call<QuestionsListResponseSchema>, t: Throwable) {
                    networkCallFailed()
                }
            })
    }

    private fun bindQuestions(questionSchemas: List<QuestionSchema>) {
        val questions: MutableList<Question> = ArrayList<Question>(questionSchemas.size)
        for (questionSchema in questionSchemas) {
            questions.add(
                Question(
                    questionSchema.questionId.toString(),
                    questionSchema.title.toString()
                )
            )
        }
        viewMvc.bindQuestions(questions)
    }

    private fun networkCallFailed() {
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
    }

    override fun onQuestionClicked(question: Question) {
        QuestionDetailsActivity.start(this, question.id)
    }
}