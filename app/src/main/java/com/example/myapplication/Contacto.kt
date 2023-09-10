package com.example.myapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contacto(val nombre: String, val imagenResId: Int, val numero:String) : Parcelable
