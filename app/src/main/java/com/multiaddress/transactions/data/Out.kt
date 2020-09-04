package com.multiaddress.transactions.data

import com.google.gson.annotations.SerializedName

data class Out (
	@SerializedName("value")
	val value : Int,
	@SerializedName("xpub")
	val xpub: Xpub?,
	@SerializedName("addr")
	val address : String
)
