package ir.amirabdol.unitconverter.type

import ir.amirabdol.unitconverter.Value
abstract class AbstractType {
    abstract val shortTypeSymbol: String
    abstract val fullTypeName: String
    abstract fun haveSameBase(type: AbstractType): Boolean
    abstract fun toCommon(value: Number): Value<*>
    override fun toString() = shortTypeSymbol
    abstract override fun equals(other: Any?): Boolean
    override fun hashCode() = shortTypeSymbol.hashCode()
}