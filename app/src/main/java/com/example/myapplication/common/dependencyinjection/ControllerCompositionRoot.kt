package com.example.myapplication.common.dependencyinjection

import android.app.Activity
import android.view.LayoutInflater
import com.example.myapplication.networking.StackoverflowApi
import com.example.myapplication.screens.common.ViewMvcFactory

class ControllerCompositionRoot(
    private val compositionRoot: CompositionRoot,
    private val activity: Activity
) {

    fun getStackoverflowApi(): StackoverflowApi = compositionRoot.getStackoverflowApi()

    private fun getLayoutInflater(): LayoutInflater = LayoutInflater.from(activity)

    fun getViewMvcFactory(): ViewMvcFactory = ViewMvcFactory(getLayoutInflater())
}