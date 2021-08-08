package com.example.myapplication.networking.questions

import com.example.myapplication.networking.owners.OwnerSchema
import com.google.gson.annotations.SerializedName

data class QuestionSchema(

    @field:SerializedName("owner")
    val owner: OwnerSchema? = null,

    @field:SerializedName("content_license")
    val contentLicense: String? = null,

    @field:SerializedName("score")
    val score: Int? = null,

    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("last_activity_date")
    val lastActivityDate: Int? = null,

    @field:SerializedName("is_answered")
    val isAnswered: Boolean? = null,

    @field:SerializedName("creation_date")
    val creationDate: Int? = null,

    @field:SerializedName("answer_count")
    val answerCount: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("body")
    val body: String? = null,

    @field:SerializedName("question_id")
    val questionId: Int? = null,

    @field:SerializedName("view_count")
    val viewCount: Int? = null,

    @field:SerializedName("tags")
    val tags: List<String?>? = null,

    @field:SerializedName("last_edit_date")
    val lastEditDate: Int? = null,

    @field:SerializedName("accepted_answer_id")
    val acceptedAnswerId: Int? = null
)
