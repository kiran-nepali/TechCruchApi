package com.example.techcrunchpostapi.network

import com.example.techcrunchpostapi.model.BasePost
import io.reactivex.Single
import retrofit2.http.GET

interface Webservice {

    @GET("v2/posts")
    fun getPostApi():Single<List<BasePost>>
}