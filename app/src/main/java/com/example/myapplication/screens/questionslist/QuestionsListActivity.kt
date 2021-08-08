package com.example.myapplication.screens.questionslist

import android.os.Bundle
import com.example.myapplication.screens.common.controllers.BaseActivity

class QuestionsListActivity : BaseActivity() {

    private lateinit var questionsListController: QuestionsListController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewMvc: QuestionsListViewMvc =
            getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(null)
        questionsListController = getCompositionRoot().getQuestionsListController()
        questionsListController.bindView(viewMvc)

        setContentView(viewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        questionsListController.onStart()
    }

    override fun onStop() {
        super.onStop()
        questionsListController.onStop()
    }
}