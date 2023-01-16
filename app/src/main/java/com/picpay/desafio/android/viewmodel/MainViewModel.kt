package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: UserRepository):
    ViewModel() {

    fun getUserList(): LiveData<List<User>> {
        return repository.getAllUsers()
    }

    fun retrieveUserListFromAPI() {
        repository.retrieveUserListFromAPI()
    }
}