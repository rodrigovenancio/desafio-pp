package com.picpay.desafio.android.repository.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.picpay.desafio.android.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(dataUser: User)

    @Query("DELETE FROM user")
    fun deleteAllRecords()

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: Int): User

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(dataUser: User)

    @Delete
    fun deleteUser(dataUser: User)
}