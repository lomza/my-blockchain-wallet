package com.multiaddress.transactions.ui.viewmodels

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.multiaddress.transactions.R
import com.multiaddress.transactions.backend.SchedulerProvider
import com.multiaddress.transactions.business.ConnectionManager
import com.multiaddress.transactions.business.MultiaddressManager
import com.multiaddress.transactions.business.SingleLiveEvent
import com.multiaddress.transactions.data.Transactions
import com.multiaddress.transactions.ui.viewmodels.TransactionsViewModel.UiEvent.ShowLoader
import com.multiaddress.transactions.ui.viewmodels.TransactionsViewModel.UiEvent.ShowWalletAndTransactions
import com.multiaddress.transactions.ui.viewmodels.TransactionsViewModel.UiEvent.ShowError
import com.multiaddress.transactions.data.Wallet
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class TransactionsViewModel @Inject constructor(
    private val multiaddressManager: MultiaddressManager,
    private val connectionManager: ConnectionManager,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val _uiEvent = SingleLiveEvent<UiEvent>()
    val uiEvent: LiveData<UiEvent> get() = _uiEvent

    /**
     * Return a network error, if there's no internet connection, otherwise
     * make a call to get wallet and transactions information.
     * Return a generic error, if the call wasn't successful.
     */
    fun fetchWalletAndTransactions() {
        if (!connectionManager.isConnection()) {
            _uiEvent.value = ShowError(R.string.load_network_error)
            return
        }

        multiaddressManager.getWalletAndTransactions(EXAMPLE_KEY)
            .doOnSubscribe {
                _uiEvent.postValue(ShowLoader)
            }
            .subscribeOn(schedulerProvider.ioScheduler())
            .observeOn(schedulerProvider.uiScheduler())
            .subscribeBy(
                onSuccess = { response ->
                    _uiEvent.value = ShowWalletAndTransactions(response.first, response.second)
                },
                onError = {
                    _uiEvent.value = ShowError(R.string.load_general_error)
                }
            )
    }

    sealed class UiEvent {
        object ShowLoader : UiEvent()
        data class ShowWalletAndTransactions(val wallet: Wallet, val transactions: Transactions) : UiEvent()
        data class ShowError(@StringRes val messageId: Int) : UiEvent()
    }

    companion object {
        const val EXAMPLE_KEY = "xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ"
    }
}
