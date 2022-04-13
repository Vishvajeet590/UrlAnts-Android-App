package com.example.urlants.di

import android.content.Context
import com.example.urlants.common.Constants
import com.example.urlants.common.SessionManager
import com.example.urlants.data.remote.UrlantsApi
import com.example.urlants.data.repository.UrlRepositoryImp
import com.example.urlants.domain.repository.UrlRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUrlantApi() : UrlantsApi{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(UrlantsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSessionManager(@ApplicationContext context: Context) : SessionManager{
        return SessionManager(context = context)
    }


    @Provides
    @Singleton
    fun provideUrlAPi(api : UrlantsApi) : UrlRepository{
        return UrlRepositoryImp(api)
    }

}