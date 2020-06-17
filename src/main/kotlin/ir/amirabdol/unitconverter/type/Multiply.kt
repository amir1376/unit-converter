@file:Suppress(
    "SpellCheckingInspection",
    "FunctionName",
    "unused",
    "MemberVisibilityCanBePrivate"
)

package ir.amirabdol.unitconverter.type

import ir.amirabdol.unitconverter.times
import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.abs

class MultipliedRowType(
    private val rowType: RowType,
    multiplyTo: Number,
    shortPrefix: String,
    fullPrefix: String
) : RowType() {
    override val factor: BigDecimal = rowType.factor * multiplyTo
    override val shortTypeSymbol: String = "$shortPrefix${rowType.shortTypeSymbol}"
    override val fullTypeName: String = "$fullPrefix${rowType.fullTypeName}"
    override fun getCommonType() = rowType.getCommonType()
    override fun haveSameBase(type: AbstractType): Boolean {
        if (type is MultipliedRowType) {
            return rowType.haveSameBase(type.rowType)
        }
        return rowType.haveSameBase(type)
    }
}

fun RowType.times(multiplyTo: Number, shortPrefix: String, fullPrefix: String): RowType {
    return MultipliedRowType(
        this,
        multiplyTo,
        shortPrefix,
        fullPrefix
    )
}

enum class PrefixOfUnit(base: Int, power: Int, val shortName: String, val fullName: String) {
    Pico(1_000, -4, "p", "pico"),
    Nano(1_000, -3, "n", "nano"),
    Micro(1_000, -2, "µ", "micro"),
    Milli(1_000, -1, "m", "milli"),
    Kilo(1_000, 1, "k", "kilo"),
    Mage(1_000, 2, "M", "mega"),
    Giga(1_000, 3, "G", "giga"),
    Tera(1_000, 4, "T", "tera"),
    Peta(1_000, 5, "T", "peta"),
    Exa(1_000, 6, "E", "exa"),
    Zetta(1_000, 7, "Z", "zetta"),
    Yotta(1_000, 8, "Y", "yotta"),

    Kibi(1_024, 1, "Ki", "kibi"),
    Mebi(1_024, 2, "Mi", "mebi"),
    Gibi(1_024, 3, "Gi", "gibi"),
    Tebi(1_024, 4, "Ti", "tebi"),
    Pebi(1_024, 5, "Pi", "pebi"),
    Exbi(1_024, 6, "Ei", "exbi"),
    Zebi(1_024, 7, "Zi", "zebi"),
    Yobi(1_024, 8, "Yi", "yobi"),
    ;

    val value: BigDecimal = with(power) {
        if (this < 0) {
            BigDecimal(base).pow(power, MathContext(abs(power) * 3))
        } else {
            BigDecimal(base).pow(power)
        }
    }

    fun prefixForType(rowType: RowType): RowType = rowType.times(value, shortName, fullName)
    operator fun invoke(rowType: RowType)=prefixForType(rowType)
}

//base 10
fun p(rowType: RowType): RowType = PrefixOfUnit.Pico.prefixForType(rowType)
fun n(rowType: RowType): RowType = PrefixOfUnit.Nano.prefixForType(rowType)
fun mic(rowType: RowType): RowType = PrefixOfUnit.Micro.prefixForType(rowType)
fun m(rowType: RowType): RowType = PrefixOfUnit.Milli.prefixForType(rowType)
fun k(rowType: RowType): RowType = PrefixOfUnit.Kilo.prefixForType(rowType)
fun M(rowType: RowType): RowType = PrefixOfUnit.Mage.prefixForType(rowType)
fun G(rowType: RowType): RowType = PrefixOfUnit.Giga.prefixForType(rowType)
fun T(rowType: RowType): RowType = PrefixOfUnit.Tera.prefixForType(rowType)

//binary
fun Ki(rowType: RowType): RowType = PrefixOfUnit.Kibi.prefixForType(rowType)
fun Mi(rowType: RowType): RowType = PrefixOfUnit.Mebi.prefixForType(rowType)
fun Gi(rowType: RowType): RowType = PrefixOfUnit.Gibi.prefixForType(rowType)