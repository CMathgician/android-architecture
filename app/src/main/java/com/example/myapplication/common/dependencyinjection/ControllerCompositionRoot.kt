package com.example.myapplication.common.dependencyinjection

import android.app.Activity
import android.view.LayoutInflater
import com.example.myapplication.networking.StackoverflowApi

class ControllerCompositionRoot(val compositionRoot: CompositionRoot, val activity: Activity) {

    fun getStackoverflowApi(): StackoverflowApi = compositionRoot.getStackoverflowApi()

    private fun getLayoutInflater(): LayoutInflater = LayoutInflater.from(activity)
}