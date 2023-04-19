package com.example.sbtechincaltest.di

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.sbtechincaltest.BaseApplication
import com.example.sbtechincaltest.model.*
import com.example.sbtechincaltest.viewmodel.PhotoUserAction
import com.example.sbtechincaltest.viewmodel.PhotoViewState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
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
    fun providePhotoRepository(service: PhotoListService): PhotoRepository {
        return PhotoRepository(service)
    }

    @Provides
    fun providePhotoInteractor(repository: PhotoRepository): PhotoInteractor {
        return PhotoInteractor(repository)
    }

    @Provides
    fun provideUserActionSubject(): PublishSubject<PhotoUserAction> {
        return PublishSubject.create<PhotoUserAction>()
    }

    @Provides
    fun provideViewStateSubject(): BehaviorSubject<PhotoViewState> {
        return BehaviorSubject.create<PhotoViewState>()
    }

    @Provides
    fun provideViewStateLiveData(): MutableLiveData<PhotoViewState> {
        return MutableLiveData<PhotoViewState>()
    }

    @Provides
    fun providePhotosLiveData(): MutableLiveData<ArrayList<Photo>> {
        return MutableLiveData<ArrayList<Photo>>()
    }

    @Provides
    fun providePhotos(): ArrayList<Photo> {
        return ArrayList<Photo>()
    }

    @Provides
    fun provideDisposables(): CompositeDisposable {
        return CompositeDisposable()
    }

}