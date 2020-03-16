package com.example.techcrunchpostapi.repository

import com.example.techcrunchpostapi.model.BasePost
import com.example.techcrunchpostapi.network.RetrofitInstance
import com.example.techcrunchpostapi.network.Webservice
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun getRetrofit(): PostRepository {
    val webservice = RetrofitInstance().retrofit.create(Webservice::class.java)
    return PostRepository(webservice)
}

class PostRepository(private val webservice: Webservice) {
    fun getPostInfo(): Single<List<BasePost>> {
        return webservice.getPostApi().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}