package com.multiaddress.transactions.backend

import com.multiaddress.transactions.data.ActiveMultiaddressKeyDetailsResponse
import io.reactivex.rxjava3.core.Single

interface MultiaddressBackend {
    fun getActiveMultiaddressKeyDetails(xPubKey: String): Single<ActiveMultiaddressKeyDetailsResponse>
}
