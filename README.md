# Unit Converter  
A kotlin library that handles unit conversation

# Usage  
here is an example to demonstrate how it works  
```kotlin
fun main(){
    val converted=(2 withUnit k(Bytes)).convertTo(M(Bits))
}
```
declaring a custom unit
```kotlin
//declaring speed units    
    val kBs=K(Bytes) per Second
    val Mbs=M(Bits) per Second
//converting from Mb/s to kB/s
    val convertedSpeed=(2 witUnit Mbs).convertTo(kBs)
```
# Supported types  
supported types are under `ir.amirabdol.unitconverter.type.availabletypes` package
you can also create types thats are depends on more than two types like Speed and more by using `FractionType`

# Contribution  
you can add another Type and its Units to this project
or if you found any bug please consider a pull request
