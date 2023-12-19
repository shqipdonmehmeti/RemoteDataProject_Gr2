package com.example.remotedataproject_gr2.helpers

import com.example.remotedataproject_gr2.api.ServiceApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Helpers {
    fun provideRetrofitInstance() : ServiceApi {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()
                .create(ServiceApi::class.java)
    }
}
