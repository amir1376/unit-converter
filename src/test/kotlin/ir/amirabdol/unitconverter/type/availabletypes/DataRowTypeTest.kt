package ir.amirabdol.unitconverter.type.availabletypes

import ir.amirabdol.unitconverter.Value
import ir.amirabdol.unitconverter.per
import ir.amirabdol.unitconverter.type.k
import ir.amirabdol.unitconverter.withUnit
import org.junit.Assert.assertEquals
import org.junit.Test

internal class DataRowTypeTest {
    @Test
    fun test() {
        kotlin.run {
            val converted: Value<Second> = (2 withUnit Hour).convertTo(
                Second
            )
            assertEquals(converted, (3600 * 2 withUnit Second))
        }
        kotlin.run {
            val converted = (0 withUnit Bytes per Second).convertTo(
                k(Bytes) per Second
            )
            assertEquals(converted, 0 withUnit k(Bytes) per Second )
        }
    }
}