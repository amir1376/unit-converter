# unit-converter
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