package com.example.techcrunchpostapi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.techcrunchpostapi.R
import com.example.techcrunchpostapi.model.BasePost
import kotlinx.android.synthetic.main.post_view_holder.view.*

class PostAdapter(private val post: List<BasePost>) : RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.post_view_holder,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return post.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.title.text = post[position].title.rendered
        holder.excerpt.text = post[position].excerpt.rendered
    }
}

class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title = view.tv_title
    val excerpt = view.tv_excerpt
}
