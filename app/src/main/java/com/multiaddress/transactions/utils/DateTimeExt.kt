package com.multiaddress.transactions.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Converter from epoch time (e.g. 1547223190) to a human-readable date (e.g. 11 Jan 2019 4:13pm).
 */
fun Long.toDateTime(): String {
    val dateFormat = SimpleDateFormat("d MMM yyyy h:mm a", Locale.getDefault())
    val date = Date((this * 1000))

    return dateFormat.format(date)
}
