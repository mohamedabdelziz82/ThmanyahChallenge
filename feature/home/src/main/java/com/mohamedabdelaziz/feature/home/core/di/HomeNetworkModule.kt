package com.mohamedabdelaziz.feature.home.core.di

import com.mohamedabdelaziz.feature.home.core.api.HomeApiService
import com.mohamedabdelaziz.feature.home.data.remote.source.RemoteHomeSectionsSource
import com.mohamedabdelaziz.feature.home.data.remote.source.RemoteHomeSectionsSourceImpl
import com.mohamedabdelaziz.feature.home.data.remote.source.RemoteSearchSectionsSource
import com.mohamedabdelaziz.feature.home.data.remote.source.RemoteSearchSectionsSourceImpl
import com.mohamedabdelaziz.feature.home.data.repository.HomeSectionsRepositoryImpl
import com.mohamedabdelaziz.feature.home.data.repository.SearchSectionsRepositoryImpl
import com.mohamedabdelaziz.feature.home.domain.repository.HomeSectionsRepository
import com.mohamedabdelaziz.feature.home.domain.repository.SearchSectionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object HomeNetworkModule {

    @Provides
    @Singleton
    fun provideHomeApiService(retrofit: Retrofit): HomeApiService =
        retrofit.create(HomeApiService::class.java)

    @Provides
    @Singleton
    fun provideHomeSectionsRepository(
        remote: RemoteHomeSectionsSource,
        dispatcher: CoroutineDispatcher
    ): HomeSectionsRepository {
        return HomeSectionsRepositoryImpl(remote, dispatcher)
    }

    @Provides
    @Singleton
    fun provideRemoteHomeSectionsSource(
        apiService: HomeApiService
    ): RemoteHomeSectionsSource =
        RemoteHomeSectionsSourceImpl(apiService)

    @Provides
    @Singleton
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
    @Provides
    @Singleton
    fun provideRemoteSearchSectionsSource(
        apiService: HomeApiService
    ): RemoteSearchSectionsSource =
        RemoteSearchSectionsSourceImpl(apiService)

    @Provides
    @Singleton
    fun provideSearchSectionsRepository(
        remote: RemoteSearchSectionsSource,
        dispatcher: CoroutineDispatcher
    ): SearchSectionsRepository {
        return SearchSectionsRepositoryImpl(remote, dispatcher)
    }


}