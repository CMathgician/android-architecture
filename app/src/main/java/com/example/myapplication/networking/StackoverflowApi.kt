package com.example.myapplication.networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StackoverflowApi {
    @GET("/questions?order=desc&sort=activity&site=stackoverflow")
    fun fetchLastActiveQuestions(@Query("pagesize") pageSize: Int?): Call<QuestionsListResponseSchema?>?
}