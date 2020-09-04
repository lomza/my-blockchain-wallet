package com.multiaddress.transactions.backend

import com.multiaddress.transactions.data.ActiveMultiaddressKeyDetailsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MultiaddressApi {

    @GET("multiaddr")
    fun getActiveMultiaddressKeyDetails(@Query("active") xPubKey: String): Single<ActiveMultiaddressKeyDetailsResponse>
}
