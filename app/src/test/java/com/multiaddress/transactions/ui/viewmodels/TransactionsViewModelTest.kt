package com.multiaddress.transactions.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.multiaddress.transactions.aWallet
import com.multiaddress.transactions.R
import com.multiaddress.transactions.RxTrampolineTestRule
import com.multiaddress.transactions.backend.SchedulerProvider
import com.multiaddress.transactions.business.ConnectionManager
import com.multiaddress.transactions.business.MultiaddressManager
import com.nhaarman.mockitokotlin2.*
import io.reactivex.rxjava3.core.Single
import org.assertj.core.api.Assertions.assertThat
import com.multiaddress.transactions.ui.viewmodels.TransactionsViewModel.UiEvent.ShowError
import com.multiaddress.transactions.ui.viewmodels.TransactionsViewModel.UiEvent.ShowLoader
import com.multiaddress.transactions.ui.viewmodels.TransactionsViewModel.UiEvent.ShowWalletAndTransactions
import com.multiaddress.transactions.transactionsList
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TransactionsViewModelTest {

    @Rule
    @JvmField
    val liveDataRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val rxRule = RxTrampolineTestRule()

    @Mock
    lateinit var multiaddressManager: MultiaddressManager

    @Mock
    lateinit var connectionManager: ConnectionManager

    private lateinit var viewModel: TransactionsViewModel

    @Before
    fun setup() {
        viewModel = TransactionsViewModel(multiaddressManager, connectionManager, SchedulerProvider())
    }

    @Test
    fun `Should emit ShowError with network error message when there is no connection`() {
        whenever(connectionManager.isConnection()).thenReturn(false)

        viewModel.fetchWalletAndTransactions()

        verify(connectionManager, times(1)).isConnection()
        verifyZeroInteractions(multiaddressManager)
        assertThat(viewModel.uiEvent.value).isEqualTo(ShowError(R.string.load_network_error))
    }

    @Test
    fun `Should emit ShowError with general error message when the call wasn't successful`() {
        whenever(connectionManager.isConnection()).thenReturn(true)
        whenever(multiaddressManager.getWalletAndTransactions(TransactionsViewModel.EXAMPLE_KEY)).thenReturn(
            Single.error(Throwable("Error"))
        )
        val observer = mock<Observer<in TransactionsViewModel.UiEvent>>()
        viewModel.uiEvent.observeForever(observer)

        viewModel.fetchWalletAndTransactions()

        verify(connectionManager, times(1)).isConnection()
        verify(multiaddressManager, times(1)).getWalletAndTransactions(TransactionsViewModel.EXAMPLE_KEY)

        inOrder(observer) {
            verify(observer).onChanged(ShowLoader)
            verify(observer).onChanged(ShowError(R.string.load_general_error))
        }
    }

    @Test
    fun `Should emit ShowWalletAndTransactions with wallet and transactions when the call was successful`() {
        whenever(connectionManager.isConnection()).thenReturn(true)
        whenever(multiaddressManager.getWalletAndTransactions(TransactionsViewModel.EXAMPLE_KEY)).thenReturn(
            Single.just(aWallet to transactionsList)
        )
        val observer = mock<Observer<in TransactionsViewModel.UiEvent>>()
        viewModel.uiEvent.observeForever(observer)

        viewModel.fetchWalletAndTransactions()

        verify(connectionManager, times(1)).isConnection()
        verify(multiaddressManager, times(1)).getWalletAndTransactions(TransactionsViewModel.EXAMPLE_KEY)

        inOrder(observer) {
            verify(observer).onChanged(ShowLoader )
            verify(observer).onChanged(ShowWalletAndTransactions(
                aWallet,
                transactionsList
            ))
        }
    }
}
