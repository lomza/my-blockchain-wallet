package com.multiaddress.transactions.backend

import com.multiaddress.transactions.aWallet
import com.multiaddress.transactions.anAddress
import com.multiaddress.transactions.anxPubKey
import com.multiaddress.transactions.data.ActiveMultiaddressKeyDetailsResponse
import com.multiaddress.transactions.transactionsList
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MultiaddressBackendImplTest {

    @Mock
    private lateinit var api: MultiaddressApi

    @InjectMocks
    private lateinit var backend: MultiaddressBackendImpl

    @Test
    fun `Get active multiaddress key details backend makes the corresponding api call`() {
        whenever(api.getActiveMultiaddressKeyDetails(anxPubKey)).thenReturn(
            Single.just(
                ActiveMultiaddressKeyDetailsResponse(
                    addresses = anAddress,
                    wallet = aWallet,
                    txs = transactionsList
                )
            )
        )

        backend.getActiveMultiaddressKeyDetails(anxPubKey).subscribe()

        verify(api, times(1)).getActiveMultiaddressKeyDetails(anxPubKey)
    }
}
