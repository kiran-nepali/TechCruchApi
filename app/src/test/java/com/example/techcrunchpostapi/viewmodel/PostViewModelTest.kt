package com.example.techcrunchpostapi.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.techcrunchpostapi.model.BasePost
import com.example.techcrunchpostapi.model.Content
import com.example.techcrunchpostapi.model.Excerpt
import com.example.techcrunchpostapi.model.Title
import com.example.techcrunchpostapi.repository.PostRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: PostViewModel

    @MockK
    private lateinit var repository: PostRepository
    private var postobserver: Observer<List<BasePost>> = mock()
    private var errorobserver: Observer<String> = mock()
    private var errormsg = "no data found"

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = PostViewModel(repository)
        viewModel.post.observeForever(postobserver)
        viewModel.error.observeForever(errorobserver)
    }

    @Test
    fun `when Api call is made, it returns empty list`() {
        val emptyList: List<BasePost> = emptyList()
        every { (repository.getPostInfo()) } returns (Single.just(emptyList))
        viewModel.getPost()
        verify(postobserver).onChanged(emptyList)
        verify(errorobserver, never()).onChanged(errormsg)
    }

    @Test
    fun `when Api call is successful, it returns data`() {
        val listOfPost = listOf(
            BasePost(
                0, "a", "a", "a", "a", "a", "a", "a", "a", Title("a"), Content("a", true),
                Excerpt("a", true)
            ),
            BasePost(
                1, "b", "b", "b", "b", "b", "b", "b", "b", Title("b"), Content("b", true),
                Excerpt("b", false)
            )
        )
        every { repository.getPostInfo() } returns (Single.just(listOfPost))
        viewModel.getPost()
        verify(postobserver).onChanged(listOfPost)
        verify(errorobserver, never()).onChanged(errormsg)
    }

    @Test
    fun `when Api call is failure, it returns error`() {
        every { repository.getPostInfo() } returns Single.error(RuntimeException(errormsg))
        viewModel.getPost()
        verify(errorobserver).onChanged(errormsg)
    }

}