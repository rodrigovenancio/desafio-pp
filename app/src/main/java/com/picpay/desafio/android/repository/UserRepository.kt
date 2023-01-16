package com.picpay.desafio.android.repository

import androidx.lifecycle.LiveData
import com.picpay.desafio.android.repository.local.UserDao
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.network.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepository  @Inject constructor(
    private val userService: UserService,
    private val userDao: UserDao
) {

    fun getAllUsers(): LiveData<List<User>> {
        return userDao.getAllUsers()
    }

    fun insertUser(dataUser: User) {
        userDao.insertUsers(dataUser)
    }

    fun retrieveUserListFromAPI() {
        val call: Call<List<User>> = userService.getUsers()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                if (response.isSuccessful) {
                    userDao.deleteAllRecords()
                    response.body()?.forEach {
                        insertUser(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
            }
        })
    }
}