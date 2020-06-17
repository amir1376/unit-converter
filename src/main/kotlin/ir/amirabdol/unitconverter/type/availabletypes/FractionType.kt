package ir.amir_ab.unitconverter.type.availabletypes

import ir.amir_ab.unitconverter.Value
import ir.amir_ab.unitconverter.div
import ir.amir_ab.unitconverter.per
import ir.amir_ab.unitconverter.type.AbstractType
import ir.amir_ab.unitconverter.bigDecimal

class FractionType<A : AbstractType, B : AbstractType>(
    val up: A, val down: B
) : AbstractType() {
    override fun haveSameBase(type: AbstractType): Boolean = type is FractionType<*, *>
    override val shortTypeSymbol: String = "${up.shortTypeSymbol}/${down.shortTypeSymbol}"
    override val fullTypeName: String = "${up.fullTypeName}/${down.fullTypeName}"

    override fun toCommon(value: Number): Value<*> {
        val a = up.toCommon(value)
        val b = down.toCommon(1)
        return Value(a.value.bigDecimal() / b.value, a.type per b.type)
    }

    override fun equals(other: Any?): Boolean {
        return other === this || (other is FractionType<*, *> &&
                up == other.up &&
                down == other.down)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + up.hashCode()
        result = 31 * result + down.hashCode()
        return result
    }
}