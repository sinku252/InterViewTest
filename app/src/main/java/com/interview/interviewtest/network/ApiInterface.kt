package com.interview.interviewtest.network

import com.interview.interviewtest.data.PostModel
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    /*@GET("products.json")
    fun fetchAllPosts(): Call<List<PostModel>>*/

    @GET("products.json")
    fun fetchAllPosts(@Query("brand") brand: String): Call<List<PostModel>>

}