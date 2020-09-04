package com.multiaddress.transactions.business

import com.multiaddress.transactions.data.Transactions
import com.multiaddress.transactions.data.Wallet
import io.reactivex.rxjava3.core.Single

/**
 * This manager takes care of all the manipulations with wallet and transactions,
 * retrieved from the backend.
 */
interface MultiaddressManager {
    fun getWalletAndTransactions(xPubKey: String): Single<Pair<Wallet, Transactions>>
}
