package ir.amirabdol.unitconverter.type.availabletypes

import ir.amirabdol.unitconverter.bigDecimal
import ir.amirabdol.unitconverter.type.AbstractType
import ir.amirabdol.unitconverter.type.RowType
import java.math.BigDecimal

abstract class LengthType(
    override val factor: BigDecimal,
    override val shortTypeSymbol: String,
    override val fullTypeName: String
) : RowType() {
    override fun getCommonType() = Metre
    override fun haveSameBase(type: AbstractType) = type is LengthType
}
object Metre:LengthType("1".bigDecimal(),"m","metre")
object Yard:LengthType("1.09361".bigDecimal(),"yd","yard")
object Inch:LengthType("39.3701".bigDecimal(),"in","inch")