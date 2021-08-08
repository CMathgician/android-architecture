package com.example.myapplication.networking

import com.google.gson.annotations.SerializedName

class QuestionDetailsResponseSchema(@field:SerializedName("items") val questions: List<QuestionSchema>) {

    val question: QuestionSchema
        get() = questions[0]

}
