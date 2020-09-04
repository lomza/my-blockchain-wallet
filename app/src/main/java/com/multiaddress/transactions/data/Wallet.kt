package com.multiaddress.transactions.data

import com.google.gson.annotations.SerializedName

data class Wallet (
	@SerializedName("final_balance")
	val finalBalance : Long,
	@SerializedName("total_received")
	val totalReceived : Long,
	@SerializedName("total_sent")
	val totalSent : Long
)
