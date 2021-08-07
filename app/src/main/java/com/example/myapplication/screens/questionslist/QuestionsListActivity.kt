package com.example.myapplication.screens.questionslist

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.common.Constants
import com.example.myapplication.networking.QuestionSchema
import com.example.myapplication.networking.QuestionsListResponseSchema
import com.example.myapplication.networking.StackoverflowApi
import com.example.myapplication.questions.Question
import com.example.myapplication.screens.common.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class QuestionsListActivity : BaseActivity(), QuestionsListAdapter.OnQuestionClickListener {

    private var mStackoverflowApi: StackoverflowApi? = null

    private var mLstQuestions: ListView? = null
    private var mQuestionsListAdapter: QuestionsListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.questions_list)
        mLstQuestions = findViewById(R.id.list_view_questions)
        mQuestionsListAdapter = QuestionsListAdapter(this, this)
        mLstQuestions?.setAdapter(mQuestionsListAdapter)
        mStackoverflowApi = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StackoverflowApi::class.java)
    }

    override fun onStart() {
        super.onStart()
        fetchQuestions()
    }

    private fun fetchQuestions() {
        mStackoverflowApi?.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
            ?.enqueue(object : Callback<QuestionsListResponseSchema> {
                override fun onResponse(
                    call: Call<QuestionsListResponseSchema>,
                    response: Response<QuestionsListResponseSchema>
                ) {
                    if (response.isSuccessful()) {
                        response.body()?.questions?.let { bindQuestions(it) }
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
            questions.add(Question(questionSchema.questionId.toString(),
                questionSchema.title.toString()
            ))
        }
        mQuestionsListAdapter!!.clear()
        mQuestionsListAdapter!!.addAll(questions)
        mQuestionsListAdapter!!.notifyDataSetChanged()
    }

    private fun networkCallFailed() {
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
    }

    override fun onQuestionClicked(question: Question) {
        Toast.makeText(this, question.title, Toast.LENGTH_SHORT).show()
    }
}