package com.example.myapplication

import android.app.Application
import com.example.myapplication.common.dependencyinjection.CompositionRoot

class CustomApplication: Application() {

    private lateinit var compositionRoot: CompositionRoot

    override fun onCreate() {
        super.onCreate()
        compositionRoot = CompositionRoot()
    }

    fun getCompositionRoot(): CompositionRoot = compositionRoot
}