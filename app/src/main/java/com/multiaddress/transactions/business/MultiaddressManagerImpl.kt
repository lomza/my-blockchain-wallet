package com.multiaddress.transactions.business

import com.multiaddress.transactions.backend.MultiaddressBackend
import com.multiaddress.transactions.data.Transactions
import com.multiaddress.transactions.data.Wallet
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MultiaddressManagerImpl @Inject constructor(
    private val multiaddressBackend: MultiaddressBackend
): MultiaddressManager {

    /**
     * Make a backend call with pub key and return a pair of wallet and transactions, sorted from newest to oldest.
     */
    override fun getWalletAndTransactions(xPubKey: String): Single<Pair<Wallet, Transactions>> {
        return multiaddressBackend.getActiveMultiaddressKeyDetails(xPubKey).map { response ->
            response.wallet to response.txs.sortedByDescending { it.time }
        }
    }
}
