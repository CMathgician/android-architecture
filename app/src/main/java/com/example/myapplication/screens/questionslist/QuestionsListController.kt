package com.example.myapplication.screens.questionslist

import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.questions.FetchLastActiveQuestionsUseCase
import com.example.myapplication.questions.Question
import com.example.myapplication.screens.common.MessagesDisplayer
import com.example.myapplication.screens.common.ScreenNavigator
import com.example.myapplication.screens.questiondetails.QuestionDetailsActivity

class QuestionsListController(
    private val fetchLastActiveQuestionsUseCase: FetchLastActiveQuestionsUseCase,
    private val screenNavigator: ScreenNavigator,
    private val messagesDisplayer: MessagesDisplayer
) :
    QuestionsListViewMvc.Listener,
    FetchLastActiveQuestionsUseCase.Listener {

    private lateinit var viewMvc: QuestionsListViewMvc

    fun bindView(viewMvc: QuestionsListViewMvc) {
        this.viewMvc = viewMvc
    }

    fun onStart() {
        viewMvc.registerListener(this)
        fetchLastActiveQuestionsUseCase.registerListener(this)

        viewMvc.showProgressIndication()
        fetchLastActiveQuestionsUseCase.fetchLastActiveQuestionsAndNotify()
    }

    fun onStop() {
        viewMvc.unregisterListener(this)
        fetchLastActiveQuestionsUseCase.unregisterListener(this)
    }

    override fun onQuestionClicked(question: Question) {
        screenNavigator.toDialogDetails(question.id)
    }

    override fun onLastActiveQuestionsFetched(questions: List<Question>) {
        viewMvc.hideProgressIndication()
        viewMvc.bindQuestions(questions)
    }

    override fun onLastActiveQuestionsFetchFailed() {
        viewMvc.hideProgressIndication()
        messagesDisplayer.showUseCaseError()
    }
}