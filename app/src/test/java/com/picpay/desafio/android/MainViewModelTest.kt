package com.picpay.desafio.android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.model.Contact
import com.picpay.desafio.android.repository.ContactRepository
import com.picpay.desafio.android.repository.local.ContactDao
import com.picpay.desafio.android.repository.network.ContactService
import com.picpay.desafio.android.viewmodel.MainViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var mainViewModel: MainViewModel
    lateinit var contactRepository: ContactRepository

    @Mock
    lateinit var apiService: ContactService

    @Mock
    lateinit var contactDao: ContactDao

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        contactRepository = ContactRepository(apiService, contactDao)
        mainViewModel = MainViewModel(contactRepository)
    }

    @Test
    fun getAllMoviesTest() {
        runBlocking {
            // Mockito.`when`(userRepository.getAllUsers())
            //    .thenReturn((listOf<User>(User("image", "movie", 1, "john"))))
            val result  = mainViewModel.getUserList().value
            assertEquals(listOf<Contact>(Contact("image", "movie", 1, "john")), result)
        }
    }


    /*@Test
    fun `empty movie list test`() {
        runBlocking {
            Mockito.`when`(mainRepository.getAllMovies())
                .thenReturn(Response.success(listOf<Movie>()))
            mainViewModel.getAllMovies()
            val result = mainViewModel.movieList.getOrAwaitValue()
            assertEquals(listOf<Movie>(), result)
        }
    }*/

}