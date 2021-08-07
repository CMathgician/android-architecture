package com.example.myapplication.screens.common

import android.content.Context
import android.view.View
import androidx.annotation.IdRes

abstract class BaseViewMvcImpl : BaseViewMvc {
    private lateinit var rootView: View
    override fun getRootView(): View = rootView

    protected fun setRootView(view: View) {
        rootView = view
    }

    protected fun <T : View?> findViewById(@IdRes id: Int): T = getRootView().findViewById(id)

    protected fun getContext(): Context = getRootView().context
}