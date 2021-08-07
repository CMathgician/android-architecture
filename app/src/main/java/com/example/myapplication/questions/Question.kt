package com.example.myapplication.questions

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Question(
    @field:SerializedName("question_id") val id: String,
    @field:SerializedName("title") val title: String,
): Serializable
