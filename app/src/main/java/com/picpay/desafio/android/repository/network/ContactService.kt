package com.picpay.desafio.android.repository.network

import com.picpay.desafio.android.model.Contact
import retrofit2.Call
import retrofit2.http.GET


interface ContactService {

    @GET("users")
    fun getUsers(): Call<List<Contact>>

}