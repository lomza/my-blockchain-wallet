package com.multiaddress.transactions.backend

interface ServerProvider {

    fun getBlockchainBaseUrl(): String
}
