package com.example.myapplication.calculadora

sealed class Operacionescal(val symbol: String){
    object Suma: Operacionescal("+")
    object Resta: Operacionescal("-")
    object Multiplicacion: Operacionescal("x")
    object Division: Operacionescal("/")
}