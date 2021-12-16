package com.sampel.tokoonline.helper

import java.text.NumberFormat
import java.util.*

class Helper {
    fun formatRupiah(nominal: Int): String {
        return NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(nominal)
    }

    companion object{
        val BASE_URL = "http://172.16.177.133:8000/"
    }
}