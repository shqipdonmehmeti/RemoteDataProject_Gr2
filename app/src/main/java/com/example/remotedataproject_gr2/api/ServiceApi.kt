package com.example.remotedataproject_gr2.api

import com.example.remotedataproject_gr2.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {
    @GET("posts")
    fun getAllPosts(): Call<List<Post>>

    @GET("posts/{id}")
    fun getOnePost(@Path("id") ourId: Int): Call<Post>
}