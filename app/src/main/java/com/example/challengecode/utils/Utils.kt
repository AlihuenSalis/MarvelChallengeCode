package com.example.challengecode.utils

import java.math.BigInteger
import java.security.MessageDigest

class Utils {
    fun md5Hash(): String {
        val str = "1$PRIVATE_KEY$PUBLIC_KEY"
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(str.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }

    companion object {
        val PUBLIC_KEY = "3a783b25c80e1c44875356dd363f272d"
        val PRIVATE_KEY = "aa1141953df8c088f39a97de10008578e834580f"
    }
}