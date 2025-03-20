package com.ronaldgustmann.calculaimc_kotlin

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun calculaIMC_isCorrect_defaultLocale() {
        assertTrue("Calculo do IMC está certo", MainActivity.calculaIMC(80.0, 1.8, "pt") in 24.69 .. 24.70)
    }
}