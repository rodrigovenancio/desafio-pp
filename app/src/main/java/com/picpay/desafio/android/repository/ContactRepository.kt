package com.picpay.desafio.android.repository

import androidx.lifecycle.LiveData
import com.picpay.desafio.android.repository.local.ContactDao
import com.picpay.desafio.android.model.Contact
import com.picpay.desafio.android.repository.network.ContactService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ContactRepository  @Inject constructor(
    private val contactService: ContactService,
    private val contactDao: ContactDao
) {

    fun getAllUsers(): LiveData<List<Contact>> {
        return contactDao.getAllContacts()
    }

    fun insertUser(dataContact: Contact) {
        contactDao.insertContacts(dataContact)
    }

    fun retrieveUserListFromAPI() {
        val call: Call<List<Contact>> = contactService.getUsers()
        call.enqueue(object : Callback<List<Contact>> {
            override fun onResponse(
                call: Call<List<Contact>>,
                response: Response<List<Contact>>
            ) {
                if (response.isSuccessful) {
                    contactDao.deleteAllRecords()
                    response.body()?.forEach {
                        insertUser(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Contact>>, t: Throwable) {
            }
        })
    }
}