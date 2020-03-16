package com.example.techcrunchpostapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.techcrunchpostapi.R
import com.example.techcrunchpostapi.model.BasePost
import com.example.techcrunchpostapi.network.RetrofitInstance
import com.example.techcrunchpostapi.network.Webservice
import com.example.techcrunchpostapi.repository.PostRepository
import com.example.techcrunchpostapi.viewmodel.PostViewModel
import com.example.techcrunchpostapi.viewmodel.PostViewModelFatory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val webservice = RetrofitInstance().retrofit.create(Webservice::class.java)
    private val repository = PostRepository(webservice)
    private var factory = PostViewModelFatory(repository)
    private lateinit var viewModel: PostViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, factory).get(PostViewModel::class.java)
        viewModel.getPost()
        viewModel.post.observe(this, Observer {
            postAdaper(it)
        })
    }

    fun postAdaper(post: List<BasePost>) {
        val adapter = PostAdapter(post)
        rv_post.layoutManager = LinearLayoutManager(this)
        rv_post.adapter = adapter
    }
}
