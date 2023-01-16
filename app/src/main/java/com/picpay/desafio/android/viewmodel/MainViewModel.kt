package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.model.Contact
import com.picpay.desafio.android.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ContactRepository):
    ViewModel() {

    fun getUserList(): LiveData<List<Contact>> {
        return repository.getAllUsers()
    }

    fun retrieveUserListFromAPI() {
        repository.retrieveUserListFromAPI()
    }
}