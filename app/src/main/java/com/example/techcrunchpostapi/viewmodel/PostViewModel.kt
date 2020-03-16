package com.example.techcrunchpostapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.techcrunchpostapi.model.BasePost
import com.example.techcrunchpostapi.repository.PostRepository
import io.reactivex.disposables.CompositeDisposable

class PostViewModel(val repository: PostRepository) : ViewModel() {
    val post = MutableLiveData<List<BasePost>>()
    val error = MutableLiveData<String>()
    val disposable = CompositeDisposable()

    fun getPost() {
        disposable.add(
            repository.getPostInfo().subscribe({
                post.value = it
            }, {
                error.value = it.localizedMessage
            })
        )
    }
}