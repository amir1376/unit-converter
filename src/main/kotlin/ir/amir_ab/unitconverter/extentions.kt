@file:Suppress("unused")

package ir.amir_ab.unitconverter

import ir.amir_ab.unitconverter.type.availabletypes.FractionType
import ir.amir_ab.unitconverter.type.AbstractType

infix fun AbstractType.per(type: AbstractType) =
    FractionType(this, type)
infix fun Value<*>.per(type: AbstractType) =
    Value(
        this.value,
        FractionType(this.type, type)
    )
infix fun Number.withUnit(type: AbstractType) =
    Value(this, type)