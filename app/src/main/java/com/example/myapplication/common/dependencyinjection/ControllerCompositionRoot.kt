package com.example.myapplication.common.dependencyinjection

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import com.example.myapplication.networking.StackoverflowApi
import com.example.myapplication.questions.FetchLastActiveQuestionsUseCase
import com.example.myapplication.questions.FetchQuestionDetailsUseCase
import com.example.myapplication.screens.common.MessagesDisplayer
import com.example.myapplication.screens.common.ScreenNavigator
import com.example.myapplication.screens.common.ViewMvcFactory
import com.example.myapplication.screens.questionslist.QuestionsListController

class ControllerCompositionRoot(
    private val compositionRoot: CompositionRoot,
    private val activity: Activity
) {

    private fun getStackoverflowApi(): StackoverflowApi = compositionRoot.getStackoverflowApi()

    private fun getLayoutInflater(): LayoutInflater = LayoutInflater.from(activity)

    private fun getContext(): Context = activity

    fun getViewMvcFactory(): ViewMvcFactory = ViewMvcFactory(getLayoutInflater())

    private fun getFetchLastActiveQuestionsUseCase(): FetchLastActiveQuestionsUseCase =
        FetchLastActiveQuestionsUseCase(getStackoverflowApi())

    fun getFetchQuestionDetailsUseCase(): FetchQuestionDetailsUseCase =
        FetchQuestionDetailsUseCase(getStackoverflowApi())

    fun getQuestionsListController(): QuestionsListController {
        return QuestionsListController(
            getFetchLastActiveQuestionsUseCase(),
            getScreenNavigator(),
            getMessagesDisplayer()
        )
    }

    fun getScreenNavigator(): ScreenNavigator = ScreenNavigator(getContext())

    fun getMessagesDisplayer(): MessagesDisplayer = MessagesDisplayer(getContext())
}