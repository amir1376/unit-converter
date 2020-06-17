package ir.amir_ab.unitconverter.type.availabletypes

import org.junit.Assert.assertEquals
import org.junit.Test
import ir.amir_ab.unitconverter.Value
import ir.amir_ab.unitconverter.withUnit

internal class DataRowTypeTest {
    @Test
    fun test() {
        val converted: Value<Second> = (2 withUnit Hour).convertTo(
            Second
        )
        assertEquals(converted,(3600*2 withUnit Second))
    }
}