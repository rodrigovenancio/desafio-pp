package com.picpay.desafio.android

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.network.UserService

class ExampleService(
    private val service: UserService
) {

    fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}