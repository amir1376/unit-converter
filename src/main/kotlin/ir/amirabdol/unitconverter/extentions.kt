@file:Suppress("unused")

package ir.amirabdol.unitconverter

import ir.amirabdol.unitconverter.type.availabletypes.FractionType
import ir.amirabdol.unitconverter.type.AbstractType

infix fun AbstractType.per(type: AbstractType) =
    FractionType(this, type)
infix fun Value<*>.per(type: AbstractType) =
    Value(
        this.value,
        FractionType(this.type, type)
    )
infix fun Number.withUnit(type: AbstractType) =
    Value(this, type)