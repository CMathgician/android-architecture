package com.example.myapplication.screens.common.views

import java.util.*
import kotlin.collections.HashSet

abstract class BaseObservableViewMvcImpl<ListenerType> : BaseViewMvcImpl(),
    ObservableViewMvc<ListenerType> {

    private var listeners: HashSet<ListenerType> = HashSet()

    override fun registerListener(listener: ListenerType) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: ListenerType) {
        listeners.remove(listener)
    }

    protected fun getListeners():Set<ListenerType> = Collections.unmodifiableSet(listeners)
}