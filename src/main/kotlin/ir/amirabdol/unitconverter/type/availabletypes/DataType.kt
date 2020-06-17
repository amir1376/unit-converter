@file:Suppress("unused")

package ir.amirabdol.unitconverter.type.availabletypes

import ir.amirabdol.unitconverter.times
import ir.amirabdol.unitconverter.type.AbstractType
import ir.amirabdol.unitconverter.type.RowType
import java.math.BigDecimal

abstract class DataRowType(
    override val factor: BigDecimal,
    override val shortTypeSymbol: String,
    override val fullTypeName: String
) : RowType() {
    override fun getCommonType() = Bits
    override fun haveSameBase(type: AbstractType) = type is DataRowType
}

object Bits : DataRowType(1.toBigDecimal(), "b", "bit")
object Bytes : DataRowType(Bits.factor * 8, "B", "byte")
