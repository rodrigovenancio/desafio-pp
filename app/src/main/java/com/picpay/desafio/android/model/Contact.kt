package com.picpay.desafio.android.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "contact")
data class Contact(

    @ColumnInfo(name = "img")
    val img: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "username")
    val username: String

)