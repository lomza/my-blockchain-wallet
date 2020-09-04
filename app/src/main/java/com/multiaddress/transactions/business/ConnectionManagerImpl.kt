package com.multiaddress.transactions.business

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject

internal class ConnectionManagerImpl @Inject internal constructor(
    private val context: Context
) : ConnectionManager {

    // Yep, this check is deprecated from API 29. In a production app I would use an if/else
    // depending on API level and would listen to new connectivity events
    override fun isConnection(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

        return activeNetwork?.isConnectedOrConnecting == true
    }
}
