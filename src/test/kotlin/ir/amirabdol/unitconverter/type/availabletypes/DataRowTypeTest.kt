package ir.amirabdol.unitconverter.type.availabletypes

import org.junit.Assert.assertEquals
import org.junit.Test
import ir.amirabdol.unitconverter.Value
import ir.amirabdol.unitconverter.withUnit

internal class DataRowTypeTest {
    @Test
    fun test() {
        val converted: Value<Second> = (2 withUnit Hour).convertTo(
            Second
        )
        assertEquals(converted,(3600*2 withUnit Second))
    }
}