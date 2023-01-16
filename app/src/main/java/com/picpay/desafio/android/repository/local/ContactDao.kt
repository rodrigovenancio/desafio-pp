package com.picpay.desafio.android.repository.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.picpay.desafio.android.model.Contact

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact")
    fun getAllContacts(): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContacts(dataContact: Contact)

    @Query("DELETE FROM contact")
    fun deleteAllRecords()

    @Query("SELECT * FROM contact WHERE id = :id")
    fun getContactById(id: Int): Contact

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateContact(dataContact: Contact)

    @Delete
    fun deleteContact(dataContact: Contact)
}