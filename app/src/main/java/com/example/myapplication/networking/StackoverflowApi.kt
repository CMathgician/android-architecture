package com.example.myapplication.networking

import com.example.myapplication.networking.questions.QuestionDetailsResponseSchema
import com.example.myapplication.networking.questions.QuestionsListResponseSchema
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StackoverflowApi {
    @GET("/questions?order=desc&sort=activity&site=stackoverflow&key=f)yov8mEGrYZa1dJDb2gpg((")
    fun fetchLastActiveQuestions(@Query("pagesize") pageSize: Int?): Call<QuestionsListResponseSchema>

    @GET("/questions/{questionId}?site=stackoverflow&filter=withbody&key=f)yov8mEGrYZa1dJDb2gpg((")
    fun fetchQuestionDetails(@Path("questionId") questionId: String): Call<QuestionDetailsResponseSchema>
}