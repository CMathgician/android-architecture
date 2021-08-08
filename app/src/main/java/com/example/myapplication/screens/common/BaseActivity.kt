package com.example.myapplication.screens.common

import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.CustomApplication
import com.example.myapplication.common.dependencyinjection.CompositionRoot

open class BaseActivity: AppCompatActivity() {

    protected fun getCompositionRoot(): CompositionRoot {
        return (application as CustomApplication).getCompositionRoot()
    }
}