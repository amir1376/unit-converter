package ir.amirabdol.unitconverter.type.availabletypes

import ir.amirabdol.unitconverter.bigDecimal
import ir.amirabdol.unitconverter.type.AbstractType
import ir.amirabdol.unitconverter.type.RowType
import java.math.BigDecimal

abstract class VolumeType(
    override val factor: BigDecimal,
    override val shortTypeSymbol: String,
    override val fullTypeName: String
) : RowType() {
    override fun getCommonType() = Litre
    override fun haveSameBase(type: AbstractType) = type is VolumeType
}
object Litre:VolumeType(1.bigDecimal(),"l","litre")
object Barrel:VolumeType(159.bigDecimal(),"bbl","barrel")
object USGallon:VolumeType("0.264172".bigDecimal(),"gal","US liquid gallon")