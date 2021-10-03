package com.example.showcat

import androidx.paging.PagingSource
import com.example.showcat.data.api.model.CatApi
import com.example.showcat.data.api.retrofit.CatApiService
import com.example.showcat.data.repository.CatPagingSource
import io.mockk.MockKAnnotations
import io.mockk.unmockkAll
import io.mockk.coEvery
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.test.assertEquals

import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class PagingSourceTest {

    @MockK
    private lateinit var catApi: CatApi

    @MockK
    private lateinit var catApiService: CatApiService


    private lateinit var catPagingSource: CatPagingSource

    private val userList = listOf(
        CatApi(id = "ebf", "https://cdn2.thecatapi.com/images/ebf.jpg"),
        CatApi(id = "Yk0fGLjTm", "https://cdn2.thecatapi.com/images/Yk0fGLjTm.jpg"),
        CatApi(id = "hzoL1y2nQ", "https://cdn2.thecatapi.com/images/hzoL1y2nQ.jpg")
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        catPagingSource = CatPagingSource(catApiService)

    }

    @After
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun `when github service throw io exception, then same exception should return as load result`() {
        runBlockingTest {

            val ioException = IOException("Some message for exception")

            coEvery {
                catApiService.getPhoto(any(), any())
            } throws ioException

            assertEquals(
                actual = catPagingSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 1,
                        placeholdersEnabled = false
                    )
                ), expected = PagingSource.LoadResult.Error(ioException)
            )
        }
    }
}
