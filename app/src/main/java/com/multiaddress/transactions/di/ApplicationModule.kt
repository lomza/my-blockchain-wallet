package com.multiaddress.transactions.di

import android.content.Context
import com.multiaddress.transactions.backend.MultiaddressBackend
import com.multiaddress.transactions.business.ConnectionManager
import com.multiaddress.transactions.business.ConnectionManagerImpl
import com.multiaddress.transactions.business.MultiaddressManager
import com.multiaddress.transactions.business.MultiaddressManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {
    @Provides
    @Singleton
    fun provideMultiaddressManager(
        multiaddressBackend: MultiaddressBackend
    ): MultiaddressManager = MultiaddressManagerImpl(multiaddressBackend)

    @Provides
    @Singleton
    internal fun provideConnectionManager(@ApplicationContext context: Context): ConnectionManager {
        return ConnectionManagerImpl(context)
    }
}
