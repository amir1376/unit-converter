package ir.amir_ab.unitconverter.type

import ir.amir_ab.unitconverter.Value
import ir.amir_ab.unitconverter.times
import java.math.BigDecimal

abstract class RowType : AbstractType() {
    abstract val factor: BigDecimal
    abstract fun getCommonType(): RowType

    override fun toCommon(value: Number): Value<*> {
        return Value(factor * value, getCommonType())
    }

    override fun equals(other: Any?) = other === this || (other is RowType && other.factor == factor)

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + factor.hashCode()
        return result
    }
}
