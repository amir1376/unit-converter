@file:Suppress("unused")

package ir.amirabdol.unitconverter

import ir.amirabdol.unitconverter.type.AbstractType
import java.text.DecimalFormat

class Value<A : AbstractType>(
    val value: Number,
    val type: A
) : Number() {
    override fun toByte() = value.toByte()
    override fun toChar() = value.toChar()
    override fun toDouble() = value.toDouble()
    override fun toFloat() = value.toFloat()
    override fun toInt() = value.toInt()
    override fun toLong() = value.toLong()
    override fun toShort() = value.toShort()

    private val defaultFormatter by lazy {
        DecimalFormat("#,###.00").apply {
            minimumIntegerDigits = 1
            maximumFractionDigits = Int.MAX_VALUE
            minimumFractionDigits = 0
        }
    }

    override fun toString(): String {
        return "$value $type"
    }

    fun toString(precision: Int): String {
        val t = defaultFormatter.maximumFractionDigits
        defaultFormatter.maximumFractionDigits = precision
        val o = "${defaultFormatter.format(value)} $type"
        defaultFormatter.maximumFractionDigits = t
        return o
    }

    @Suppress("UNCHECKED_CAST")
    fun <B : AbstractType> convertTo(newType: B): Value<B> {
        if (!newType.haveSameBase(type)) {
            throw IllegalArgumentException("units aren't same ${type.javaClass.simpleName},${newType.javaClass.simpleName}")
        }
        if (type == newType) return this as Value<B>
        val common = type.toCommon(value).value.bigDecimal() / newType.toCommon(1).value
        return Value(common, newType)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Value<*>

        if (value neq other.value) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }
}

