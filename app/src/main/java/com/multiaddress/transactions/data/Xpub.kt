package com.multiaddress.transactions.data

import com.google.gson.annotations.SerializedName

data class Xpub (
	@SerializedName("m")
	val m : String
)
