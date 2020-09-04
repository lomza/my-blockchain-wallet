package com.multiaddress.transactions.backend

import com.multiaddress.transactions.BuildConfig

class ServerProviderImpl : ServerProvider {

    override fun getBlockchainBaseUrl(): String =  BuildConfig.API_BLOCKCHAIN
}
