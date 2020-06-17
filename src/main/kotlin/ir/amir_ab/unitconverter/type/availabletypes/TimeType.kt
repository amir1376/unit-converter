@file:Suppress("unused")

package ir.amir_ab.unitconverter.type.availabletypes

import unitconverter.type.*
import java.math.BigDecimal
import ir.amir_ab.unitconverter.times
import ir.amir_ab.unitconverter.type.AbstractType
import ir.amir_ab.unitconverter.type.RowType

abstract class TimeRowType(
    override val factor: BigDecimal,
    override val shortTypeSymbol: String,
    override val fullTypeName: String
) : RowType() {
    override fun getCommonType() = Second
    override fun haveSameBase(type: AbstractType) = type is TimeRowType
}

object Second : TimeRowType(1.toBigDecimal(), "s", "Second")
object Day : TimeRowType(Hour.factor * 24, "d", "Day")
object Hour : TimeRowType(Minute.factor * 60, "h", "Hour")
object Minute : TimeRowType(Second.factor * 60, "min", "Minute")