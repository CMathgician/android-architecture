package com.example.myapplication.screens.common

import android.content.Context
import android.widget.Toast
import com.example.myapplication.R

class MessagesDisplayer(private val context: Context) {
    fun showUseCaseError() {
        Toast.makeText(context, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
    }
}