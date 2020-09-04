package com.multiaddress.transactions.data

import com.google.gson.annotations.SerializedName

data class ActiveMultiaddressKeyDetailsResponse(
    @SerializedName("addresses")
    val addresses: List<Address>,
    @SerializedName("wallet")
    val wallet: Wallet,
    @SerializedName("txs")
    val txs: List<Tx>
)
