package com.qiubo.intive.misc

object TextHelper {
    fun capitalize(text: String?): String {
        val builder = StringBuilder()
        if (text != null) {
            val strArray =
                text.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (s in strArray) {
                if (s.length > 1) {
                    val cap = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase()
                    builder.append(cap).append(" ")
                } else if (s.length == 1) {
                    val cap = s.toUpperCase()
                    builder.append(cap).append(" ")
                }
            }
        }
        return builder.toString()
    }
}