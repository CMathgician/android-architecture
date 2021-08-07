package com.example.myapplication.screens.common

interface ObservableViewMvc<ListenerType> : BaseViewMvc {
    fun registerListener(listener: ListenerType)
    fun unregisterListener(listener: ListenerType)
}