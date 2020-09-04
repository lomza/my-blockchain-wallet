package com.multiaddress.transactions.utils

import java.math.BigDecimal
import java.math.RoundingMode

const val BTC_IN_SATOSHIS = 1e8

fun Long.satoshisToBtc(): BigDecimal = BigDecimal.valueOf(this).divideRoundingUp(BTC_IN_SATOSHIS)

fun BigDecimal.divideRoundingUp(value: Double, precision: Int = 8): BigDecimal {
    return this.divide(BigDecimal(value), precision, RoundingMode.UP)
}
