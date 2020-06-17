@file:Suppress("unused")

package ir.amirabdol.unitconverter

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode


infix fun Number.eq(other: Number): Boolean {
    return if (this is BigDecimal || other is BigDecimal) {
        this.valueEquals(other)
    } else {
        this == other
    }
}

infix fun Number.neq(other: Number): Boolean {
    return !(this eq other)
}

internal fun BigDecimal.removeTrailingZeros(): BigDecimal {
    val chars = toString().toCharArray()
    val chSize = chars.size
    if ('.' !in chars) {
        return this
    }
    if (chars[chSize - 1] != '0') {
        return this
    }
    var endPos: Int = chSize
    f@ for (i in chSize - 2 downTo 0) {
        when (chars[i]) {
            '0' -> continue@f
            '.' -> {
                endPos = i - 1
                break@f
            }
            // in 123456789
            else -> {
                endPos = i
                break@f
            }
        }
    }
    val len = endPos + 1
    if (len <= 0) {
        return BigDecimal(0)
    }
    return BigDecimal(chars,0,len)
}

private fun Number.valueEquals(other: Number): Boolean {
    val firstTry = this == other
    if (firstTry) return true
    return bigDecimal().stripTrailingZeros() == other.bigDecimal().stripTrailingZeros()
}

fun Any.bigDecimal(): BigDecimal {
    try {
        return when (this) {
            is BigDecimal -> this
            is Int -> toBigDecimal()
            is Long -> toBigDecimal()
            is Double -> toBigDecimal()
            is Float -> toBigDecimal()
            is BigInteger -> toBigDecimal()
            else -> BigDecimal(toString())
        }
    } catch (e: NumberFormatException) {
        throw NumberFormatException(
            """
                    unit converter uses BigDecimal
                    to calculate and cant create 
                    from this number:(${toString()})
                    with reason:(${e.message})
                """.trimIndent()
        )
    }
}

internal operator fun BigDecimal.times(other: Number): BigDecimal {
    val a = this
    val b = other.bigDecimal()
    return a.multiply(b)
}

internal operator fun BigDecimal.div(other: Number): BigDecimal {
    val a = this
    val b = other.bigDecimal()
    return a.divideInternal(b, 9, RoundingMode.DOWN).removeTrailingZeros()
}

internal operator fun BigDecimal.plus(other: Number): BigDecimal {
    val a = this
    val b = other.bigDecimal()
    return a.addInternal(b)
}

internal operator fun BigDecimal.minus(other: Number): BigDecimal {
    val a = this
    val b = other.bigDecimal()
    return a.minusInternal(b)
}

private fun BigDecimal.addInternal(another: BigDecimal) = this.add(another)
private fun BigDecimal.minusInternal(another: BigDecimal) = this.subtract(another)
private fun BigDecimal.multiplyInternal(another: BigDecimal) = this.multiply(another)
private fun BigDecimal.divideInternal(another: BigDecimal, scale: Int, roundingMode: RoundingMode) =
    this.divide(another, scale, roundingMode)