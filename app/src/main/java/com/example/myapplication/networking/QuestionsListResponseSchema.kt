package com.example.myapplication.networking

import com.google.gson.annotations.SerializedName

data class QuestionsListResponseSchema(

	@field:SerializedName("has_more")
	val hasMore: Boolean,

	@field:SerializedName("items")
	val questions: List<QuestionSchema>
)
