package com.multiaddress.transactions.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class MoneyExtTest {

    @Test
    fun `Should correctly convert satoshis to btc 1`() {
        val satoshis: Long = -155928
        val expectedBtcValue = BigDecimal.valueOf (-0.00155928)

        val btcValue = satoshis.satoshisToBtc()

        assertEquals(expectedBtcValue, btcValue)
    }

    @Test
    fun `Should correctly convert satoshis to btc 2`() {
        val satoshis: Long = -17791
        val expectedBtcValue = BigDecimal.valueOf (-0.00017791)

        val btcValue = satoshis.satoshisToBtc()

        assertEquals(expectedBtcValue, btcValue)
    }

    @Test
    fun `Should correctly convert satoshis to btc 3`() {
        val satoshis: Long = 559182
        val expectedBtcValue = BigDecimal.valueOf (0.00559182)

        val btcValue = satoshis.satoshisToBtc()

        assertEquals(expectedBtcValue, btcValue)
    }

    @Test
    fun `Should correctly convert satoshis to btc 4`() {
        val satoshis: Long = 262_374_279
        val expectedBtcValue = BigDecimal.valueOf (2.62374279)

        val btcValue = satoshis.satoshisToBtc()

        assertEquals(expectedBtcValue, btcValue)
    }
}
