package com.example.showcat

import androidx.paging.PagingSource
import com.example.showcat.data.api.retrofit.CatApiService
import com.example.showcat.data.repository.CatPagingSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class PagingSourceTest {

    @MockK
    private lateinit var catApiService: CatApiService

    private lateinit var catPagingSource: CatPagingSource

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
