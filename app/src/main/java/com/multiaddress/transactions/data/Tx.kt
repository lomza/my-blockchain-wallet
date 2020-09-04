package com.multiaddress.transactions.data

import com.google.gson.annotations.SerializedName

data class Tx (
	@SerializedName("hash")
	val hash : String,
	@SerializedName("size")
	val size : Int,
	@SerializedName("weight")
	val weight : Int,
	@SerializedName("fee")
	val fee : Long,
	@SerializedName("result")
	val result : Long,
	@SerializedName("balance")
	val balance : Int,
	@SerializedName("time")
	val time : Long,
	@SerializedName("inputs")
	val inputs : List<Input>,
	@SerializedName("out")
	val out : List<Out>
)
