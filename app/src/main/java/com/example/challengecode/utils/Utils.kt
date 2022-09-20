package com.example.challengecode.utils

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.example.challengecode.R
import java.math.BigInteger
import java.security.MessageDigest
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Utils {
    fun md5Hash(): String {
        val str = "1$PRIVATE_KEY$PUBLIC_KEY"
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(str.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }

    fun monthToString(month: Int): String {
        return when (month) {
            1 -> "Enero"
            2 -> "Febrero"
            3 -> "Marzo"
            4 -> "Abril"
            5 -> "Mayo"
            6 -> "Junio"
            7 -> "Julio"
            8 -> "Agosto"
            9 -> "Septiembre"
            10 -> "Octubre"
            11 -> "Noviembre"
            12 -> "Diciembre"
            else -> ""
        }
    }

    fun setDate(date: String?, context: Context) : String {
        var formatDate = ""
         date?.let {
            val parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            val year = parsedDate.year
            val day = parsedDate.dayOfMonth
            val month = monthToString(parsedDate.month.value)
            formatDate = "$day de $month $year"
        } ?: run {
            formatDate = context.getString(R.string.toDefine)
        }
        return formatDate
    }

    companion object {
        val PUBLIC_KEY = "3a783b25c80e1c44875356dd363f272d"
        val PRIVATE_KEY = "aa1141953df8c088f39a97de10008578e834580f"
    }

}