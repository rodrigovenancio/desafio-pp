package com.picpay.desafio.android

import com.picpay.desafio.android.model.Contact
import com.picpay.desafio.android.repository.network.ContactService

class ExampleService(
    private val service: ContactService
) {

    fun example(): List<Contact> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}