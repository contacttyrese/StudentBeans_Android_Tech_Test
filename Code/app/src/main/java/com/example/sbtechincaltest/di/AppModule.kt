package com.example.sbtechincaltest.di

import android.content.Context
import com.example.sbtechincaltest.BaseApplication
import com.example.sbtechincaltest.model.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Provides
    fun providePhotolistService(): PhotoListService {
        return PhotoListRetroFit.createPhotoListService()
    }

    @Provides
    fun provideDisposables(): CompositeDisposable {
        return CompositeDisposable()
    }

}