package com.example.myapplication.common.dependencyinjection

import com.example.myapplication.common.Constants
import com.example.myapplication.networking.StackoverflowApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CompositionRoot {
    private var retrofit: Retrofit? = null

    fun getStackoverflowApi(): StackoverflowApi {
        return getRetrofit().create(StackoverflowApi::class.java)
    }

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}