package com.kiljae.mygallery.common

import java.text.DecimalFormat

object Util {

    @JvmStatic
    fun intToBraketText(value : Int): String{
        return String.format("(%d)", value) as String
    }
}