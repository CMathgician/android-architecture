package com.example.myapplication.screens.common

import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.CustomApplication
import com.example.myapplication.common.dependencyinjection.ControllerCompositionRoot

open class BaseActivity : AppCompatActivity() {

    private val controllerCompositionRoot: ControllerCompositionRoot by lazy {
        ControllerCompositionRoot(
            (application as CustomApplication).getCompositionRoot(),
            this
        )
    }

    protected fun getCompositionRoot(): ControllerCompositionRoot {
        return controllerCompositionRoot
    }
}