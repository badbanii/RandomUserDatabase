package com.tuly.userdatabase.di

import android.content.Context
import androidx.room.Room
import com.tuly.userdatabase.data.local.UserDatabase
import com.tuly.userdatabase.data.remote.api.UserApi
import com.tuly.userdatabase.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserApi() : UserApi
    {
        return Retrofit.Builder()
            .baseUrl(Constants.USERS_API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }).build())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context.applicationContext,
        UserDatabase::class.java,
        "usersdb.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase)=userDatabase.userDao

    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

}