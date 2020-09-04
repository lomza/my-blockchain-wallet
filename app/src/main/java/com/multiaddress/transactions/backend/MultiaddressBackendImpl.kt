package com.multiaddress.transactions.backend

import com.multiaddress.transactions.data.ActiveMultiaddressKeyDetailsResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

internal class MultiaddressBackendImpl @Inject constructor(
    private val multiaddressApi: MultiaddressApi
) : MultiaddressBackend {

    override fun getActiveMultiaddressKeyDetails(xPubKey: String): Single<ActiveMultiaddressKeyDetailsResponse> {
        return multiaddressApi.getActiveMultiaddressKeyDetails(xPubKey)
    }
}
