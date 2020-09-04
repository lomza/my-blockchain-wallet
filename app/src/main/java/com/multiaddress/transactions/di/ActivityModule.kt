package com.multiaddress.transactions.di

import com.multiaddress.transactions.backend.SchedulerProvider
import com.multiaddress.transactions.business.ConnectionManager
import com.multiaddress.transactions.business.MultiaddressManager
import com.multiaddress.transactions.ui.viewmodels.TransactionsViewModelFactory
import com.multiaddress.transactions.ui.viewmodels.TransactionsViewModelFactoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun provideTransactionsListViewModelFactory(
        multiaddressManager: MultiaddressManager,
        connectionManager: ConnectionManager,
        schedulerProvider: SchedulerProvider
    ): TransactionsViewModelFactory {
        return TransactionsViewModelFactoryImpl(multiaddressManager, connectionManager, schedulerProvider)
    }
}
