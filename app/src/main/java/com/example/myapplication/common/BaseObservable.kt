package com.example.myapplication.common

import java.util.*
import java.util.concurrent.ConcurrentHashMap

abstract class BaseObservable<LISTENER_CLASS> {
    // thread-safe set of listeners
    private val listeners = Collections.newSetFromMap(
        ConcurrentHashMap<LISTENER_CLASS, Boolean>(1)
    )

    fun registerListener(listener: LISTENER_CLASS) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: LISTENER_CLASS) {
        listeners.remove(listener)
    }

    protected fun getListeners(): Set<LISTENER_CLASS> {
        return Collections.unmodifiableSet(listeners)
    }
}