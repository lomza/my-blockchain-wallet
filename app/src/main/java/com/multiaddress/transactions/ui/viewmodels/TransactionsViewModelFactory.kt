package com.multiaddress.transactions.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.multiaddress.transactions.backend.SchedulerProvider
import com.multiaddress.transactions.business.ConnectionManager
import com.multiaddress.transactions.business.MultiaddressManager
import javax.inject.Inject

interface TransactionsViewModelFactory : ViewModelProvider.Factory

@Suppress("UNCHECKED_CAST")
class TransactionsViewModelFactoryImpl @Inject constructor(
    private val multiaddressManager: MultiaddressManager,
    private val connectionManager: ConnectionManager,
    private val schedulerProvider: SchedulerProvider
) : TransactionsViewModelFactory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TransactionsViewModel(multiaddressManager, connectionManager, schedulerProvider) as T
    }
}
