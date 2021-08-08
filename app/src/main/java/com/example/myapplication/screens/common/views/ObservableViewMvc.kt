package com.example.myapplication.screens.common.views

interface ObservableViewMvc<ListenerType> : BaseViewMvc {
    fun registerListener(listener: ListenerType)
    fun unregisterListener(listener: ListenerType)
}