package com.multiaddress.transactions.business

import com.multiaddress.transactions.aWallet
import com.multiaddress.transactions.anAddress
import com.multiaddress.transactions.anxPubKey
import com.multiaddress.transactions.backend.MultiaddressBackend
import com.multiaddress.transactions.data.ActiveMultiaddressKeyDetailsResponse
import com.multiaddress.transactions.data.Transactions
import com.multiaddress.transactions.data.Wallet
import com.multiaddress.transactions.transactionsList
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MultiaddressManagerImplTest {

    @Mock
    private lateinit var backend: MultiaddressBackend

    @InjectMocks
    private lateinit var manager: MultiaddressManagerImpl

    @Test
    fun `Get wallet and transactions from manager makes the corresponding backend call`() {
        val response = ActiveMultiaddressKeyDetailsResponse(
            addresses = anAddress,
            wallet = aWallet,
            txs = transactionsList
        )
        whenever(backend.getActiveMultiaddressKeyDetails(anxPubKey)).thenReturn(Single.just(response))
        val observer = TestObserver<Pair<Wallet, Transactions>>()

        manager.getWalletAndTransactions(anxPubKey).subscribe(observer)

        verify(backend, times(1)).getActiveMultiaddressKeyDetails(anxPubKey)
        observer.assertComplete()
        observer.assertValueCount(1)
        observer.assertValue { it == aWallet to transactionsList.sortedByDescending { tx -> tx.time } }
    }
}
