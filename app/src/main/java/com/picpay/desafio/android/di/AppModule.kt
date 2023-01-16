package com.picpay.desafio.android.di

import android.app.Application
import com.picpay.desafio.android.repository.local.UserDao
import com.picpay.desafio.android.repository.local.UserDatabase
import com.picpay.desafio.android.repository.network.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val baseUrl = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

    @Provides
    @Singleton
    fun getAppDatabase(context: Application): UserDatabase {
        return UserDatabase.getAppDBInstance(context)
    }

    @Provides
    @Singleton
    fun getUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.getUserDao()
    }

    @Provides
    @Singleton
    fun getUserServiceInstance(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}