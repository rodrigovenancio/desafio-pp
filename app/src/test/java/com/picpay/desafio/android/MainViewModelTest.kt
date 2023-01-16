package com.picpay.desafio.android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.repository.local.UserDao
import com.picpay.desafio.android.repository.network.UserService
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
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var mainViewModel: MainViewModel
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var apiService: UserService

    @Mock
    lateinit var userDao: UserDao

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        userRepository = UserRepository(apiService, userDao)
        mainViewModel = MainViewModel(userRepository)
    }

    @Test
    fun getAllMoviesTest() {
        runBlocking {
            // Mockito.`when`(userRepository.getAllUsers())
            //    .thenReturn((listOf<User>(User("image", "movie", 1, "john"))))
            val result  = mainViewModel.getUserList().value
            assertEquals(listOf<User>(User("image", "movie", 1, "john")), result)
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