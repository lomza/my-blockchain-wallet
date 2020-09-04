package com.multiaddress.transactions.di

import com.multiaddress.transactions.backend.*
import com.multiaddress.transactions.backend.MultiaddressBackendImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object BackendModule {

    @Provides
    @Singleton
    fun provideServerEnvironment(): ServerProvider {
        return ServerProviderImpl()
    }

    @Provides
    @Singleton
    fun provideMultiaddressApi(environment: ServerProvider): MultiaddressApi {
        return Retrofit.Builder()
            .baseUrl(environment.getBlockchainBaseUrl())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MultiaddressApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMultiaddressBackend(multiaddressApi: MultiaddressApi): MultiaddressBackend {
        return MultiaddressBackendImpl(multiaddressApi)
    }
}
