package com.multiaddress.transactions.data

import com.google.gson.annotations.SerializedName

data class Input (
	@SerializedName("prev_out")
	val previousOut : Out
)
