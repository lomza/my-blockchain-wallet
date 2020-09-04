package com.multiaddress.transactions.data

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("address")
    val address: String,
    @SerializedName("final_balance")
    val finalBalance: Int,
    @SerializedName("total_received")
    val totalReceived: Int,
    @SerializedName("total_sent")
    val totalSent: Int
)
