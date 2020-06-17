@file:Suppress("unused")

package ir.amir_ab.unitconverter.type.availabletypes

import ir.amir_ab.unitconverter.times
import ir.amir_ab.unitconverter.type.AbstractType
import ir.amir_ab.unitconverter.type.RowType
import unitconverter.type.*
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
